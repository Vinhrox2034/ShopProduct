CREATE DATABASE ShopProductDB
GO
USE ShopProductDB
GO
CREATE TABLE Users (
    userId VARCHAR(20) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    fullname NVARCHAR(100),
    role VARCHAR(10) CHECK (role IN ('USER', 'ADMIN')),
    active BIT DEFAULT 1
)
CREATE TABLE Products (
    productId VARCHAR(20) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    image VARCHAR(255),
    description NVARCHAR(MAX),
    price FLOAT,
    views INT DEFAULT 0,
    active BIT DEFAULT 1
)
CREATE TABLE Favorites (
    favoriteId BIGINT IDENTITY PRIMARY KEY,
    userId VARCHAR(20),
    productId VARCHAR(20),
    likeDate DATETIME DEFAULT GETDATE(),

    CONSTRAINT fk_fav_user FOREIGN KEY (userId)
        REFERENCES Users(userId),

    CONSTRAINT fk_fav_product FOREIGN KEY (productId)
        REFERENCES Products(productId),

    CONSTRAINT uq_fav UNIQUE (userId, productId)
)
CREATE TABLE Shares (
    shareId BIGINT IDENTITY PRIMARY KEY,
    userId VARCHAR(20),
    productId VARCHAR(20),
    emails NVARCHAR(255),
    shareDate DATETIME DEFAULT GETDATE(),

    CONSTRAINT fk_share_user FOREIGN KEY (userId)
        REFERENCES Users(userId),

    CONSTRAINT fk_share_product FOREIGN KEY (productId)
        REFERENCES Products(productId)
)
CREATE INDEX idx_fav_product ON Favorites(productId)
CREATE INDEX idx_share_product ON Shares(productId)
INSERT INTO Users VALUES
('U01','admin','123','admin@gmail.com',N'Quản trị','ADMIN',1),
('U02','user1','123','user1@gmail.com',N'Người dùng 1','USER',1)

INSERT INTO Products VALUES
('P01',N'Sản phẩm A','a.jpg',N'Mô tả A',100000,10,1),
('P02',N'Sản phẩm B','b.jpg',N'Mô tả B',200000,5,1)

INSERT INTO Shares (userId, productId, emails)
VALUES ('U02', 'P01', 'test1@gmail.com,test2@gmail.com')

SELECT p.name, COUNT(f.favoriteId) AS totalLikes
FROM Products p
LEFT JOIN Favorites f ON p.productId = f.productId
GROUP BY p.name
SELECT u.username, u.email
FROM Favorites f
JOIN Users u ON f.userId = u.userId
WHERE f.productId = 'P01'
SELECT u.username, s.emails, s.shareDate
FROM Shares s
JOIN Users u ON s.userId = u.userId
WHERE s.productId = 'P01'
SELECT 
    p.productId,
    p.name,
    COUNT(f.favoriteId) AS totalLikes
FROM Products p
LEFT JOIN Favorites f ON p.productId = f.productId
GROUP BY p.productId, p.name
