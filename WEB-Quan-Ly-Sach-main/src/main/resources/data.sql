-- Create admin account
INSERT INTO account (username, password, email, role, created_at, updated_at)
VALUES ('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'admin@gmail.com', 'ROLE_ADMIN', '2023-05-01', '2023-05-01');

-- Create admin info
INSERT INTO admin (name, address, phone, account_id)
VALUES ('Administrator', 'Hanoi', '0123456789', 1);

-- Create user account
INSERT INTO account (username, password, email, role, created_at, updated_at)
VALUES ('user', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'user@gmail.com', 'ROLE_USER', '2023-05-01', '2023-05-01');

-- Create user info
INSERT INTO user (name, address, phone, account_id)
VALUES ('User', 'Ho Chi Minh City', '0987654321', 2);

-- Create categories
INSERT INTO category (name, created_at, updated_at)
VALUES ('Textbooks', '2023-05-01', '2023-05-01'),
       ('Literature', '2023-05-01', '2023-05-01'),
       ('Children Books', '2023-05-01', '2023-05-01'),
       ('Life Skills Books', '2023-05-01', '2023-05-01');

-- Create authors
INSERT INTO author (name, description, created_at, updated_at)
VALUES ('Nguyen Nhat Anh', 'Famous author of children books', '2023-05-01', '2023-05-01'),
       ('To Hoai', 'Famous author of folk literature', '2023-05-01', '2023-05-01'),
       ('Dale Carnegie', 'Famous author of life skills books', '2023-05-01', '2023-05-01');

-- Create books
INSERT INTO book (title, author_id, category_id, date_publication, price, number_page, number_sold, number_in_stock, description, created_at, updated_at)
VALUES ('Yellow Flowers on Green Grass', 1, 2, '2010-05-01', 85000, 200, 150, 50, 'Yellow Flowers on Green Grass is a novel by writer Nguyen Nhat Anh', '2023-05-01', '2023-05-01'),
       ('Adventures of a Cricket', 2, 3, '1941-05-01', 65000, 150, 200, 30, 'Adventures of a Cricket is the most famous and distinctive prose work of writer To Hoai', '2023-05-01', '2023-05-01'),
       ('How to Win Friends and Influence People', 3, 4, '1936-05-01', 90000, 250, 300, 40, 'How to Win Friends and Influence People is the most famous, best-selling and influential book of all time', '2023-05-01', '2023-05-01'),
       ('Give Me a Ticket to Childhood', 1, 2, '2008-05-01', 80000, 180, 120, 60, 'Give Me a Ticket to Childhood is a novel by writer Nguyen Nhat Anh', '2023-05-01', '2023-05-01'),
       ('Effective Communication Skills', 3, 4, '1945-05-01', 75000, 220, 100, 70, 'Book teaches about effective communication skills in life', '2023-05-01', '2023-05-01');

-- Create comments
INSERT INTO comment (content, star, user_id, book_id, created_at, updated_at)
VALUES ('The book is very good and useful', 5, 1, 1, '2023-05-01', '2023-05-01'),
       ('I really like this book', 4, 1, 2, '2023-05-01', '2023-05-01'); 