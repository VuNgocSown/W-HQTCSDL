package com.thunga.web.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Transactional
    public void init() {
        loadImagesForBooks();
    }

    private void loadImagesForBooks() {
        try {
            // Đường dẫn đến thư mục hình ảnh
            ClassPathResource resource = new ClassPathResource("static/images/books");
            File imageDir = resource.getFile();
            
            if (imageDir.exists()) {
                File[] imageFiles = imageDir.listFiles();
                if (imageFiles != null && imageFiles.length > 0) {
                    System.out.println("Đang nạp hình ảnh cho sách...");
                    
                    // Tải hình ảnh mặc định trước
                    byte[] defaultImageBytes = null;
                    for (File imageFile : imageFiles) {
                        if (imageFile.getName().equals("img-01.jpg")) {
                            defaultImageBytes = Files.readAllBytes(Paths.get(imageFile.getAbsolutePath()));
                            break;
                        }
                    }
                    
                    // Truy vấn tất cả sách không có hình ảnh
                    List<Integer> bookIds = jdbcTemplate.queryForList(
                            "SELECT id FROM book WHERE image IS NULL", Integer.class);
                    
                    System.out.println("Tìm thấy " + bookIds.size() + " sách cần cập nhật hình ảnh");
                    
                    // Lấy tên sách cho mỗi ID
                    for (Integer bookId : bookIds) {
                        String title = jdbcTemplate.queryForObject(
                                "SELECT title FROM book WHERE id = ?", String.class, bookId);
                        
                        boolean imageFound = false;
                        
                        if (title != null) {
                            String lowerTitle = title.toLowerCase().trim();
                            
                            // Tìm file hình ảnh phù hợp với tên sách
                            for (File imageFile : imageFiles) {
                                String fileName = imageFile.getName().toLowerCase();
                                
                                // Kiểm tra tên file có chứa một phần của tên sách không
                                if (fileName.contains(lowerTitle.substring(0, Math.min(lowerTitle.length(), 5))) || 
                                    (lowerTitle.length() > 10 && fileName.contains(lowerTitle.substring(0, 10)))) {
                                    
                                    try {
                                        byte[] imageBytes = Files.readAllBytes(Paths.get(imageFile.getAbsolutePath()));
                                        
                                        // Cập nhật hình ảnh vào database
                                        jdbcTemplate.update(
                                                "UPDATE book SET image = ? WHERE id = ?",
                                                imageBytes, bookId);
                                        
                                        imageFound = true;
                                        System.out.println("Đã nạp hình ảnh cho sách: " + title);
                                        break;
                                    } catch (Exception e) {
                                        System.err.println("Lỗi khi nạp hình ảnh cho " + title + ": " + e.getMessage());
                                    }
                                }
                            }
                            
                            // Nếu không tìm thấy hình ảnh phù hợp, sử dụng hình mặc định
                            if (!imageFound && defaultImageBytes != null) {
                                jdbcTemplate.update(
                                        "UPDATE book SET image = ? WHERE id = ?",
                                        defaultImageBytes, bookId);
                                System.out.println("Đã nạp hình mặc định cho sách: " + title);
                            }
                        }
                    }
                    
                    System.out.println("Hoàn thành nạp hình ảnh cho sách.");
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi nạp hình ảnh cho sách: " + e.getMessage());
        }
    }
} 