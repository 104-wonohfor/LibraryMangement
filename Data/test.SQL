SELECT * FROM dbo.Reader
SELECT * FROM dbo.ReaderCardID
SELECT * FROM dbo.Librarian
SELECT * FROM dbo.Book
SELECT * FROM dbo.BookCopy
SELECT * FROM dbo.Author
SELECT * FROM dbo.BookAuthor
SELECT * FROM dbo.BookRequest
SELECT * FROM dbo.YourBookshelf
SELECT * FROM dbo.Request
SELECT * FROM dbo.NumberReadRequest


SELECT 
br.BookRequestID AS 'Request ID', 
br.ListBookCode AS 'Book Code', 
br.ReaderCardID AS 'Card ID', 
br.RequestStatus AS 'Status',
br.DateBorrowed AS 'Borrow Date', 
br.DateDue AS 'Due Date', 
br.DateReturned AS 'Return Date', 
br.PenaltyFee AS 'Penalty Fee', 
br.RequestNotes AS 'Note' 
FROM BookRequest br 
WHERE br.ReaderCardID LIKE N'%2021%';


SELECT * FROM Reader WHERE ReaderCardID = '20218217' AND ReaderPassword = '762412471846146737986207318625420722243'
UPDATE Librarian SET LibrarianPassword = '1962026656160185351301320480154111117132155' WHERE LibrarianPhoneNumber = '0987654322'

SELECT * FROM Reader WHERE ReaderCardID = '20218217' AND ReaderPassword = ''OR 1=1;--'

SELECT * FROM dbo.Book WHERE BookID = 'E3.DN.1994';

SELECT * FROM Librarian WHERE LibrarianName LIKE N'%Linh%'

SELECT 
	b.BookTitle AS Title, 
	b.BookID AS ID, 
	b.BookCategory AS 'Category', 
	STRING_AGG(a.AuthorName, ', ') AS 'Author', 
	b.YearPublished AS 'Year Published', 
	b.BookPublisher AS Publisher, 
	b.BookTopic AS Topic, 
	b.BookDescription AS Description, 
	b.BookNotes AS Notes, 
	b.BookQuantity AS Quantity 
FROM 
	Book b 
LEFT JOIN 
	BookAuthor ba ON b.BookID = ba.BookID 
LEFT JOIN 
	Author a ON ba.AuthorID = a.AuthorID 
WHERE BookTitle LIKE N'%Lịch sử%' AND AuthorName LIKE N'%Nguyễn%'
GROUP BY b.BookID, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity;






SELECT 
b.BookTitle AS Title, 
b.BookID AS ID, 
bc.BookCode AS 'Code', 
b.BookCategory AS 'Category', 
bc.BookLocation AS 'Location', 
bc.BookStatus AS 'Status', 
STRING_AGG(a.AuthorName, ', ') AS 'Author', 
b.YearPublished AS 'Year Published', 
b.BookPublisher AS Publisher, 
b.BookTopic AS Topic, 
b.BookDescription AS Description, 
b.BookNotes AS Notes, 
b.BookQuantity AS Quantity FROM Book b 
LEFT JOIN BookAuthor ba ON b.BookID = ba.BookID 
LEFT JOIN Author a ON ba.AuthorID = a.AuthorID 
LEFT JOIN BookCopy bc ON b.BookID = bc.BookID 
WHERE b.BookID LIKE N'%1911%' 
GROUP BY b.BookID, bc.BookCode, bc.BookLocation, bc.BookStatus, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity;


-- Kiểm tra xem b.Quantity và count(BookID) có giống nhau không, thêm cột hiệu số để kiểm tra
SELECT b.BookID, b.BookQuantity, COUNT(bc.BookID) AS Quantity, b.BookQuantity - COUNT(bc.BookID) AS Difference
FROM Book b
LEFT JOIN BookCopy bc ON b.BookID = bc.BookID
GROUP BY b.BookID, b.BookQuantity
ORDER BY b.BookID
GO


-- Select From reader card where ReaderCardID = Like DG  và lấy ra số lớn nhất sau ký tự DG
SELECT MAX(CAST(SUBSTRING(ReaderCardID, 3, LEN(ReaderCardID)) AS INT)) AS LatestReaderCardID FROM dbo.ReaderCardID WHERE ReaderCardID LIKE 'DG%'
SELECT * FROM dbo.ReaderCardID
SELECT * FROM dbo.Reader


UPDATE ReaderCardID SET CardStatus = 'Requested' WHERE ReaderCardID = 'DG10'

UPDATE dbo.Book SET BookQuantity = 1 WHERE BookID = 'M1.NT.2012'

INSERT INTO dbo.BookCopy(BookID, BookCode, BookLocation, BookStatus) VALUES ('M1.NT.2012', 'M1.NT.2012', 'Location not yet specified', 'Not Available')

DELETE dbo.ReaderCardID WHERE ReaderCardID = 'DG10'

DELETE dbo.Reader WHERE ReaderCardID = 'DG10'

UPDATE dbo.YourBookshelf SET ListBookCode = 'A2.LT.2001.C3,A1.NA.2004' WHERE ReaderCardID = 'DG9'

SELECT ListBookCode FROM YourBookshelf WHERE ReaderCardID ='DG9'

SELECT * FROM dbo.YourBookshelf

SELECT * FROM dbo.BookCopy WHERE BookStatus != 'Available'


SELECT 
b.BookID, 
b.BookTitle, 
b.BookCategory,
b.YearPublished,
b.BookPublisher, 
b.BookTopic, 
b.BookDescription, 
b.BookNotes,
bc.BookCode, 
bc.BookLocation,
bc.BookStatus 
FROM Book b 
JOIN BookCopy bc 
ON b.BookID = bc.BookID 
WHERE bc.BookCode ='A1.NA.2004'

UPDATE dbo.YourBookshelf SET ListBookCode = 'G2.CK.2006.C2,G3.NL.2011,G1.ND.2003,H1.GG.2012' WHERE ReaderCardID = '20218217'