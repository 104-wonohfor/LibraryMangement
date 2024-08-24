CREATE DATABASE QuanLyThuVien;
GO

USE QuanLyThuVien;
GO

CREATE TABLE Book
(
    BookID NVARCHAR(50) PRIMARY KEY,
    BookTitle NVARCHAR(500) NOT NULL,
    BookTopic NVARCHAR(200),
    BookDescription NVARCHAR(500),
    BookNotes NVARCHAR(1000),
    BookCategory NVARCHAR(10) NOT NULL,
    YearPublished INT,
    BookPublisher NVARCHAR(500),
    BookQuantity INT,
);

CREATE TABLE Author
(
    AuthorID INT IDENTITY PRIMARY KEY,
    AuthorName NVARCHAR(100) NOT NULL,
);

CREATE TABLE BookAuthor
(
    BookID NVARCHAR(50) NOT NULL,
    AuthorID INT NOT NULL,
);

CREATE TABLE BookCopy
(
    BookID NVARCHAR(50) NOT NULL,
    BookCode NVARCHAR(50) PRIMARY KEY,
    BookLocation NVARCHAR(150),
    BookStatus NVARCHAR(150) NOT NULL,
);

CREATE TABLE Reader
(
    ReaderName NVARCHAR(100) NOT NULL,
    ReaderDOB DATE NOT NULL,
    ReaderAddress NVARCHAR(100) NOT NULL,
    ReaderGender NVARCHAR(10) NOT NULL,
    ReaderPhoneNumber NVARCHAR(50) NOT NULL UNIQUE,
    ReaderEmail NVARCHAR(50) NOT NULL UNIQUE,
    ReaderIdentificationNumber NVARCHAR(50) NOT NULL PRIMARY KEY,
    ReaderCardID NVARCHAR(50),
    ReaderPassword NVARCHAR(100) NOT NULL DEFAULT '15410012614513998108181121232209311017111612',
);

CREATE TABLE ReaderCardID
(
    ReaderCardID NVARCHAR(50) PRIMARY KEY,
    CardPIN NVARCHAR(10) NOT NULL DEFAULT '082024',
    CardStatus NVARCHAR(50) NOT NULL DEFAULT 'Requested',
    DateIssued DATE,
    DateExpired DATE,
);
 
CREATE TABLE Librarian
(
    LibrarianName NVARCHAR(100) NOT NULL,
    LibrarianDOB DATE NOT NULL,
    LibrarianAddress NVARCHAR(100) NOT NULL,
    LibrarianGender NVARCHAR(10) NOT NULL,
    LibrarianPhoneNumber NVARCHAR(50) NOT NULL UNIQUE,
    LibrarianEmail NVARCHAR(100) UNIQUE,
    LibrarianIdentificationNumber NVARCHAR(50) NOT NULL PRIMARY KEY,
    LibrarianPassword NVARCHAR(255) NOT NULL DEFAULT '216125107150691248917719582916031223229252',
    LibrarianDepartment NVARCHAR(150) NOT NULL,
    LibrarianPosition NVARCHAR(150) NOT NULL,
    LibrarianWorkplace NVARCHAR(150) NOT NULL,
);

CREATE TABLE BookRequest
(
    BookRequestID INT IDENTITY PRIMARY KEY,
    ListBookCode NVARCHAR(250) NOT NULL,
    ReaderCardID NVARCHAR(50) NOT NULL,
    RequestStatus NVARCHAR(50) NOT NULL,
    DateBorrowed DATE,
    DateDue DATE,
    DateReturned DATE,
    PenaltyFee DECIMAL(10, 2) DEFAULT 0,
    RequestNotes NVARCHAR(500) DEFAULT 'No notes',
);

CREATE TABLE YourBookshelf
(
    ReaderCardID NVARCHAR(50) NOT NULL PRIMARY KEY,
    ListBookCode NVARCHAR(250),
)

CREATE TABLE Request (
    requestID INT PRIMARY KEY IDENTITY(1,1),
    description NVARCHAR(255) NOT NULL,
);

CREATE TABLE NumberReadRequest (
    LibrarianIdentificationNumber NVARCHAR(20) PRIMARY KEY,
    numReadRequest INT NOT NULL,
);


-- Insert into Request
INSERT INTO Request(description)
VALUES
('A new room booking was requested by Tokugawa Ieyasu'),
('ROOM 101 requested a SERVICE'),
('ROOM 101 requested a SERVICE'),
('ROOM 101 requested a SERVICE'),
('A new room booking was requested by Hojo Hidetada');


-- Insert into TrackStaffNumberReadRequest
INSERT INTO NumberReadRequest(LibrarianIdentificationNumber, numReadRequest)
VALUES
('040203001921', 0),
('040203001922', 0),
('040203001923', 0),
('040203001924', 0),
('040203001925', 0),
('040203001926', 0),
('040203001927', 0),
('040203001928', 0),
('040203001929', 0),
('040203001930', 0),
('040203001931', 0),
('040203001932', 0),
('040203001933', 0),
('040203001934', 0),
('040203001935', 0),
('040203001936', 0),
('040203001937', 0),
('040203001938', 0),
('040203001939', 0),
('040203001940', 0),
('040203001941', 0),
('040203001942', 0),
('040203001943', 0),
('040203001944', 0),
('040203001945', 0),
('040203001946', 0),
('040203001947', 0),
('040203001948', 0),
('040203001949', 0),
('040203001950', 0),
('040203001951', 0),
('040203001952', 0),
('040203001953', 0),
('040203001954', 0),
('040203001955', 0),
('040203001956', 0),
('040203001957', 0),
('040203001958', 0),
('040203001959', 0),
('040203001960', 0),
('040203001961', 0),
('040203001962', 0),
('040203001963', 0);


ALTER TABLE BookAuthor
ADD CONSTRAINT FK_BookAuthor_Book FOREIGN KEY (BookID) REFERENCES Book(BookID),
    CONSTRAINT FK_BookAuthor_Author FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID);

ALTER TABLE BookCopy
ADD CONSTRAINT FK_BookCopy_Book FOREIGN KEY (BookID) REFERENCES Book(BookID);

-- ALTER TABLE Reader
-- ADD CONSTRAINT FK_Reader_ReaderCardID FOREIGN KEY (ReaderCardID) REFERENCES ReaderCardID(ReaderCardID);

-- ALTER TABLE BookRequest
-- ADD CONSTRAINT FK_BookRequest_ReaderCardID FOREIGN KEY (ReaderCardID) REFERENCES ReaderCardID(ReaderCardID);




INSERT INTO Author(AuthorName)
VALUES
(N'Unknown'),
(N'Nguyễn Trung Anh'),
(N'Ngô Được'),
(N'Phạm Viên Phương'),
(N'Lưu Thị Xuân Thuỷ'),
(N'Lưu Văn Hy'),
(N'Michael D. Harkavy'),
(N'Đoàn Quang Thọ'),
(N'Phạm Văn Sinh'),
(N'Nguyễn Đăng Thục'), -- 10
(N'Bùi Thị Thanh Hương'),
(N'Nguyễn Văn Đại'),
(N'Phạm Mạnh Thảo'),
(N'Nguyễn Kim Loan'),
(N'Vũ Dương Ninh'),
(N'Trung tâm khoa học xã hội và nhân văn quốc gia. Viện khảo cổ học'),
(N'Lê Thành Lân'),
(N'Nguyễn Đình Chiến'),
(N'Văn Đức Thanh'),
(N'Chu Hữu Chí'), -- 20
(N'Khương Thiếu Ba'),
(N'Ngô Văn Tuyển'),
(N'Thái Hoàng'),
(N'Nguyễn Quốc Tuấn'),
(N'Nguyễn Minh Nguyệt'),
(N'Kate Scarborough'),
(N'Nguyễn Thái Yên Hương'),
(N'Vũ Văn Dzi'),
(N'Đào Huy Ngọc'),
(N'Bùi Thanh Sơn'), -- 30
(N'Nguyễn Vũ Tùng'),
(N'Đặng Cẩm Tú'),
(N'Rankin Hugh'),
(N'John Escott'),
(N'Katiuska Blanco'),
(N'Carlos González Irago'),
(N'Paul B. Goodwin, Jr.'),
(N'Andrew W. Ghillyer'),
(N'Nguyễn Đình Dương'),
(N'Chang Kang-tsung'), -- 40
(N'Nguyễn Văn Lợi'),
(N'Gerald Guthrie'),
(N'James L. Bruning'),
(N'Dương Xuân Ngọc'),
(N'Trần Thành'),
(N'Mai Hồng Quỳ'),
(N'Vũ Quang');





INSERT INTO Book(BookTitle, BookTopic, BookDescription, BookNotes, BookCategory, YearPublished, BookPublisher, BookID, BookQuantity)
VALUES
(N'Những kỷ lục thế giới / Nguyễn Trung Anh, Ngô Được, Phạm Viên Phương biên dịch',N'Thế giới - Kỷ lục',N'266 tr; 28cm',N'Giới thiệu các kỷ lục thế giới về: con người, thế giới tự nhiên, thế giới hiện đại, thế giới thực dụng, những kỳ công kiến tạo, khoa học và công nghệ, âm nhạc và giải trí, thể thao, nghệ thuật.',N'A',2004,N'Hà Nội: Văn hóa thông tin', N'A1.NA.2004', 2),
(N'Chuyện lạ đó đây / Lưu Thị Xuân Thuỷ sưu tầm và tuyển chọn',NULL,N'637 tr.; 19cm',NULL,N'A',2001,N'Hà Nội: Thanh niên',N'A2.LT.2001', 4),
(N'Tri thức thế giới : bạn có thể là nhà vô tuyến điện tài tử / Lưu Văn Hy và nhóm Trí Tri biên soạn',N'Tri thức',N'234 tr.; 19cm',NULL,N'A',2003,N'Hà Nội : Văn hoá thông tin', N'A3.LH.2003', 2),
(N'The American spectrum encyclopedia : the new illustrated home reference guide / Michael D. Harkavy, editor-in-chief.',N'Encyclopedias and dictionaries.',N'1312 p. : ill. (some col.), col. maps; 29 cm.',N'A concise, general-subject encyclopedia with color and black-and-white illustrations, maps, and charts.',N'A',1991,N'New York : American Booksellers Association : Spectrum Database Pub. : Distributed to the trade by Booksellers Order Service, c1991.', N'A4.MH.1991', 3),
(N'Đại cương lịch sử triết học / Đoàn Quang Thọ, Phạm Văn Sinh (chủ biên)',N'Lịch sử - Triết học',N'263 tr.; 19 cm.',NULL,N'B',1997,N'Hà Nội : Thống kê', N'B1.DT.1997', 1),
(N'Lịch sử triết học Phương Đông. Tập 1, Trung Hoa thời kỳ khởi điểm của triết học: (Thời đại Tây Chu và Đông Chu) / Nguyễn Đăng Thục',N'Triết học -- Lịch sử',N'411 tr. ; 19 cm.',N'Giới thiệu về lịch sử triết học phương Đông (đặc biệt là Trung Hoa) qua các thời kỳ và đặc điểm phát triển của từng giai đoạn.',N'B',1991,N'T.P. Hồ Chí Minh : Nxb. T.P. Hồ Chí Minh', N'B2.NT.1991', 2),
(N'Lịch sử triết học Phương Đông. Tập 2, Trung Hoa thời kỳ hoàn thành của triết học: (Từ Chiến Quốc đến Tiến Hán)',N'Triết học -- Lịch sử',N'457 tr. ; 19 cm.',N'Giới thiệu về thời đại chiến quốc: Môn đệ của Khổng tử; Triết lý đại học trung-dung; Triết lý nhân sinh của Mạnh Tử; Luân lý của Mạnh Tử; Triết học chính trị của Mạnh Tử; Tâm linh thần bí; Mạnh Tử với các trào lưu tư tưởng đương thời; Danh học; Công Tôn Long; Triết lý ở đạo đức kinh-lão tử thần bí; Trang Tử với lão học hoàn thành; Triết học của Trang Tử; Vũ trụ quan và nhân sinh quan Tuân Tử; Triết học chính trị của Tuân Tử; Pháp học; Pháp gia với đạo gia;',N'B',1991,N'T.P. Hồ Chí Minh : Nxb. T.P. Hồ Chí Minh', N'B3.NT.1991', 2),
(N'Lịch sử triết học Phương Đông. Tập 3, Ấn Độ từ phật đà tới phật nguyên thuỷ ',N'Triết học -- Lịch sử',N'310 tr. ; 19 cm.',N'Lịch sử triết học Ấn Độ. Giao thời Brahmana. Triết học Upanisad. Nhân sinh quan. Thời đại Bà La Môn-Phật. Phật học nguyên thuỷ. Tâm lý học về ngã. Luân lý học Phật giáo.',N'B',1991,N'T.P. Hồ Chí Minh : Nxb. T.P. Hồ Chí Minh', N'B4.NT.1991', 2),
(N'Khái lược lịch sử triết học / Bùi Thị Thanh Hương, Nguyễn Văn Đại',N'Lịch sử triết học',N'254 tr.; 21 cm.',N'Gồm 4 phần: Triết học Ấn Độ và Trung Quốc cổ, trung đại; Lịch sử triết học phương tây; Sự ra đời và phát triển của triết học Mác-Lênin; Lịch sử tư tưởng triết học Việt Nam.',N'B',2013,N'H.: Chính trị - Hành Chính', N'B5.BH.2013', 1),
(N'Bách khoa thư các nền văn hoá thế giới - Encyclopedia of World Cultures',N'Thế giới -- Bách khoa thư -- Văn hoá',N'1779 tr. ; 27 cm.',N'Giới thiệu các nền văn hoá có ảnh hưởng lớn có nhiều thành tựu trên thế giới, các nền văn hoá phát triển rực rỡ trong quá khứ có ảnh hưởng tới các nền văn hoá khác.',N'C',2003,N'Hà Nội : Văn hoá Thông tin',N'C1.PT.2003', 3),
(N'Lịch sử văn minh Thế giới / Vũ Dương Ninh chủ biên...[và những người khác]',N'Thế giới -- Văn minh -- Lịch sử',N'372 tr. ; 20 cm.',N'Giới thiệu về: văn minh Bắc Phi và Tây Á; Văn minh Ấn Độ, Trung Quốc; Văn minh khu vực Đông Nam Á; Văn minh Hy Lạp và La Mã cổ đại; Văn minh Tây Âu thời trung đại; Sự xuất hiện văn minh công nghiệp; Văn minh thế giới thế kỷ XX.',N'C',2000,N'Hà Nội : Giáo dục',N'C2.VN.2000', 1),
(N'Những phát hiện mới về khảo cổ học năm 2001 ',N'Khảo cổ học',N'900 tr. ; 27 cm.',N'Hoạt động khảo cổ học năm 2001. Hoạt động của trung tâm nghiên cứu khảo cổ học ở miền Nam năm 2001. Giới thiệu về một số trung tâm nghiên cứu khảo cổ học, các phát hiện khảo cổ học ở các tỉnh.',N'C',2002,N'Hà Nội : Khoa học xã hội',N'C3.TH.2002', 2),
(N'Năm trăm năm lịch Việt Nam (1544 - 2043) / Lê Thành Lân',N'Lịch -- Việt Nam',N'1030 tr.; 24 cm.',N'Giới thiệu chung về lịch và lịch Việt Nam. Lịch Việt Nam từ 1544 đến 2043, niên biểu lịch sử Việt Nam. Các lịch vĩnh cửu và ngày tiết khí của 100 năm (1944-2043).',N'C',2010,N'Hà Nội : Nxb. Hà Nội',N'C4.LL.2010', 1),
(N'Chiến tranh và hoà bình trong lịch sử và đương đại / Nguyễn Đình Chiến, Văn Đức Thanh',N'Hòa Bình -- Chiến tranh thế giới',N'527 tr.; 24 cm.',N'Gồm 4 chương: Chương I luận giải những vấn đè lý luận về chiến tranh và hòa bình; Chương II lược kahỏ quá trình nhận thức và giải quyết vấn đề chiến tranh và hòa bình trong lịch sử thế giới và Việt Nam, đút rút kinh nghiệm lịch sử; Chương II phân tích vấn đề chiến tranh và hòa bình trong thế giới đương đại và khái quát những vấn đề đặt ra; Chương IV Khái quát những vấn đề cơ bản về xây dựng quốc phòng VN gắn với những nhận thức, giải quyết vấn đề chiến tranh và hòa bình.',N'D',2016,N'Hà Nội: Chính trị Quốc gia Sự thật',N'D1.NC.2016', 1),
(N'Thế giới 5000 năm / Chu Hữu Chí, Khương Thiếu Ba chủ biên; Ngô Văn Tuyển, Thái Hoàng chỉnh lý bổ sung; Nguyễn Quốc Tuấn sưu tầm',N'Thế giới -- Lịch sử',N'852 tr. ; 27 cm.',N'Trình bày những vấn đề lịch sử, chính trị, văn hoá, tôn giáo của thế giới từ thời cổ đại đến hiện đại.',N'D',2000,N'Hà Nội : Văn hoá Thông tin',N'D2.CT.2000', 1),
(N'Nước Pháp và những trải nghiệm / Nguyễn Minh Nguyệt',N'Pháp',N'151 tr.; 21 cm.',N'Tập hợp những kinh nghiệm của cá nhân và tập thể, những câu chuyện riêng, chung về cuộc sống thường ngày trên đất Pháp',N'D',2012,N'Hà Nội : Lao động',N'D3.NN.2012', 10),
(N'Tôn Đức Thắng : Tiểu sử',N'Tôn Đức Thắng',N'255 tr.; 21 cm.',N'Chương trình viết tiểu sử các đồng chí lãnh đạo của Đảng và nhà nước - Thời niên thiếu và những hoạt động yêu nước đầu tiên của Tôn Đức Thắng, những cống hiến to lớn đối với cách mạng Việt Nam và thế giới.',N'D',2007,N'Hà Nội : Chính trị Quốc gia',N'D4.U5.2007', 2),
(N'Ancient Greece / by Kate Scarborough.',N'Hy Lạp -- Lịch sử',N'46 p. : col. ill., col. maps; 28 cm. ; in folder ([4] p. : col. ill. ; 29 cm.)',N'Folder contains a turn-and-learn wheel of famous Greeks and famous Greek art and architecture.',N'D',2008,N'London ; New York : Dk Publishing',N'D5.KS.2008', 1),
(N'Lịch sử Hoa Kỳ giai đoạn lập quốc đến thế kỷ XIX / Nguyễn Thái Yên Hương',N'Lịch sử -- Hoa Kỳ',N'207 tr.; 24 cm.',N'Sách gồm 5 chương được trình bày theo từng giai đoạn lịch sử từ thời kỳ trước khi có phát kiến địa lý của Christopher Columbus(1492) đến thập niên cuối của thế kỷ XIX. Chuyến đi phát kiến của Columbus đã đánh dấu một giai đoạn lịch sử mới trong lịch sử thế giới, vì kể từ thời điểm này, quá trình di dân và đặt cơ sở ban đầu cho sự hình thành Hợp chúng quốc Hoa Kỳ bắt đầu. Giai đoạn cuối thế kỷ XIX là thời kỳ chấm dứt việc tái thiết của nước Mỹ sau Nội chiến, Mỹ trở thành một quốc gia được thế giới ghi nhận và bắt đầu tham gia vào hệ thống chính trị quốc tế.',N'E',2015,N'H.: Giáo dục Việt Nam',N'E1.NH.2015', 1),
(N'Những chân trời Mỹ : Vòng quanh 50 tiểu bang Hoa Kỳ: mắt thấy tai nghe',N'Lịch sử -- Mỹ',N'735 tr.; 22 cm.',N'Trình bày những khám phá của Vũ Văn Dzi về các bang ở Mỹ: lịch sử, tập quán địa điểm, tổ chức y tế, giáo dục sinh hoạt cộng đồng của người Việt ở các bang.',N'E',2008,N'Hà Nội : Thế Giới',N'E2.VD.2008', 3),
(N'Hợp chủng quốc Hoa Kỳ/ Đào Huy Ngọc chủ biên; Nguyễn Thái Yên Hương, Bùi Thanh Sơn',N'Hoa Kỳ',N'151 tr. ; 19 cm',N'Lịch sử phát triển và địa lí, dân cư trong hợp chủng quốc Hoa Kỳ',N'E',1994,N'Hà Nội: Nhà xuất bản Chính trị quốc gia',N'E3.DN.1994', 1),
(N'Quan hệ Mỹ - Trung trong nửa đầu thế kỷ XXI : Mối quan hệ nước lớn kiểu mới? / Nguyễn Vũ Tùng, Đặng Cẩm Tú',N'Quan hệ -- Thế kỷ XXI -- Mỹ -- Trung Quốc',N'359 tr.; 21 cm.',N'Tóm tắt lịch sử quan hệ Mỹ - Trung Quốc từ thời kỳ Chiến tranh lạnh đến này; Phân tích mối quan hệ này ở các phương diện kinh tế, quấn sự; Phân tích cơ sở hình thành ý tưởng quan hệ nước lớn kiểu mới; Chiều hướng quan hệ Mỹ - Trung',N'E',2017,N'Hà Nội : Chính trị Quốc gia',N'E4.NT.2017', 1),
(N'The American revolution / Hugh F. Rankin',N'Cách mạng -- Mỹ',N'382 tr.; 21 cm.',NULL,N'E',1964,N'New York : G. P. Putnam''s Sons', N'E5.RH.1964', 1),
(N'California / John Escott',N'California',N'22 tr. ; 21 cm.',N'Oxford bookworms 2',N'F',2003,N'Oxford : Oxford University Press',N'F1.JE.2003', 1),
(N'Sống mãi như những cây trắc bách hương gia cảnh của Fidel Castro / Katiuska Blanco ; Người dịch Mạnh Tứ, Hoàng Hiệp',N'Fidel Castro',N'422 tr.; 24 cm.',NULL,N'F',2008,N'Hà Nội : Chính trị Quốc gia', N'F2.KB.2008', 1),
(N'Venezuela - Nhân quyền và Dân chủ (1999-2009) / Carlos González Irago; Nguyễn Thị Thanh Hằng, Đồng Thị Mai Thương biên dịch, Trần Thị Oanh hiệu đính',N'Chính trị -- Dân chủ -- Quyền con người -- Vênêxuêla',N'308 tr.; 21 cm.',N'Trình bày khuôn khổ Pháp lý của Quan điểm toàn diện và đa văn hóa về Nhân quyền. Quyền cơ bản về an sinh lịch sử phát triển tại Venezuela. Chủ quyền lương thực: thực phẩm - nhân quyền cơ bản đầu tiên về an sinh. Tiến tới quyền an ninh thứ hai: Giáo dục bắt buộc và miễn phí. Tiến tới quyền an sinh thứ ba: Sức khỏe cho người dân',N'F',2014,N'Hà Nội : Thế giới',N'F3.CI.2014', 1),
(N'Latin America / Dr. Paul B. Goodwin, Jr.',N'Châu Mỹ Latinh -- Lịch sử -- Thế kỷ 20',N'xii, 230 p. : ill., maps; 28 cm.',NULL,N'F',2007,N'New York : McGraw-Hill',N'F4.PJ.2007', 2),
(N'Business ethics now/ Andrew W. Ghillyer, PhD.',N'Business ethics.',N'xvi, 234 pages ; 28 cm',NULL,N'F',2018,N'New York, NY : McGraw-Hill Education',N'F5.AG.2018', 1),
(N'Công nghệ viễn thám trong nghiên cứu hệ thống đảo',N'Viễn thám - Tổng luận - Công nghệ',N'40 tr.; 30 cm',N'Trình bày tư liệu viễn thám trong nghiên cứu đảo, viễn thám quang học đa phổ, hệ thống chụp phim, hệ thống quang điện tử, viễn thám siêu cao tần. Các phương pháp phân tích thông tin, một số ứng dụng đã triển khai ở Việt Nam, nghiên cứu tại Côn Đảo, nghiên cứu tại khu vực quần đảo Trường Sa, một số nghiên cứu của các tác giả trong khu vực.',N'G',2003,N'Hà Nội : Ban Nghiên cứu dự báo chiến lược và quản lý khoa học',N'G1.ND.2003', 1),
(N'Introduction to geographic information systems',N'Địa lý - Hệ thống thông tin',N'xv, 432 p. : ill., maps; 25 cm. + 1 CD-ROM (4 3/4 in.)',N'Includes bibliographical references and index.',N'G',2006,N'Boston : McGraw-Hill Higher Education',N'G2.CK.2006', 2),
(N'GIS trong lâm nghiệp',N'Hệ thống thông tin địa lí - Lâm nghiệp',N'156 tr.; 24 cm',N'Giới thiệu kĩ thuật GIS và các công nghệ khác có liên quan trong việc phân tích dữ liệu địa lí và mô hình không gian để đưa ra quyêt định và các giải pháp sử dụng tài nguyên rừng có hiệu quả và bền vững.',N'G',2011,N'TP Hồ Chí Minh: Nhà xuất bản Nông nghiệp',N'G3.NL.2011', 1),
(N'Basic research methods : an entry to social science research',N'Khoa học xã hội - Nghiên cứu',N'1 online resource',N'Includes bibliographical references (pages 213-216) and index.',N'H',2012,N'Los Angeles : SAGE Publications',N'H1.GG.2012', 1),
(N'Hợp tuyển thơ văn Việt Nam. Tập 5, Quyển 1., Văn học dân tộc ít người.',N'Văn học hiện đại Việt Nam',N'549 tr.; 19 cm.',N'In lần thứ 2 có sửa chữa bổ sung',N'H',1979,N'Hà Nội : Văn học',N'H2.U5.1979', 1),
(N'Computational Handbook of Statistics',N'Thống kê',N'361 tr.',N'4th ed.',N'H',1996,N'Massachusetts : Addison-Wesley',N'H3.JB.1996', 1),
(N'Chính trị học Việt Nam',N'Chính trị học - Việt Nam',N'406 tr. ; 21 cm.',N'Học viện báo chí và tuyên truyền. Khoa chính trị học',N'J',2009,N'Hà Nội: Chính trị-hành chính',N'J1.DN.2009', 1),
(N'Tìm hiểu môn học chính trị học : Dưới dạng hỏi và đáp',N'Chính trị học',N'242 tr. ; 20 cm.',N'Trình bày những vấn đề về chính trị học đaị cương, lịch sử tư tưởng chính trị, thể chế chính trị thế giới đương đại, những vấn đề về phương pháp giảng dạy và nghiên cứu chính trị học, nghiên cứu tác phẩm của chủ nghĩa Mac - Lênin về chính trị, nghiên cứu tác phẩm của Hồ Chí Minh về chính trị',N'J',2005,N'Hà Nội : Lý luận Chính trị',N'J2.DN.2005', 5),
(N'Vấn đề dân chủ và Dân chủ hóa đời sống',N'Dân chủ -- Đời sống',N'110tr.; 20,5 cm.',N'Làm rõ thực chất của dân chủ trong lịch sử nhân loại, nhất là chế độ dân chủ đương đại. Chỉ ra thực chất chế độ dân chủ mà nhân dân ta đang xây dựng , những vấn đề đang đặt ra về lý luận và thực tiễn cần phải giải quyết trong xây dựng chế độ dân chủ xã hội chủ nghĩa ở Việt Nam.',N'J',2015,N'Hà Nội : Lý luận Chính trị',N'J3.TT.2015', 1),
(N'Giáo trình pháp luật đại cương',N'Pháp luật - Giáo trình',N'327 tr.; 24 cm.',N'Giới thiệu đại cương nhà nước và pháp luật; Đại cương về các lĩnh vực pháp luật trong hệ thống pháp luật Việt Nam; Đại cương về pháp luật quốc tế; Đào tạo luật và nghề luật ở Việt Nam.',N'K',2014,N'Hà Nội : Đại học Sư phạm',N'K1.MQ.2014', 10),
(N'Giáo trình pháp luật đại cương',N'Pháp luật - Giáo trình',N'175 tr. ; 24 cm.',N'Nhập môn pháp luật đại cương. Khái quát về nhà nước trong mối quan hệ với pháp luật, nhà nước cộng hòa xã hội chủ nghĩa Việt Nam. Những vấn đề cơ bản về pháp luật. Các hệ thống pháp luật chủ yếu trên thế giới. Hệ thống pháp luật nước cộng hòa xã hội chủ nghĩa Việt Nam. Lĩnh vực pháp luật sở hữu trí tuệ, về khoa học - công nghệ ở Việt Nam.',N'K',2016,N'Hà Nội: Bách Khoa',N'K2.VQ.2014', 3),
(N'Cẩm nang sở hữu trí tuệ : Chính sách, pháp luật và áp dụng',N'Sở hữu trí tuệ -- Sách tra cứu',N'460 tr.; 30 cm.',N'Khái niệm về sở hữu trí tuệ. Các lĩnh vực bảo hộ sở hữu trí tuệ. Vai trò của sở hữu trí tuệ trong sự phát triển và chương trình hợp tác phát triển của WIPO. Thực thi quyền sở hữu trí tuệ. Các công ước và thoả ước quốc tế về Sở hữu trí tuệ. Quản lý và giảng dạy về sở hữu trí tuệ. Sự phát triển công nghệ và pháp lý trong lĩnh vực sở hữu trí tuệ.',N'K',2005,N'[Hà Nội] : Tổ chức sở hữu trí tuệ thế giới',N'K3.U5.2005', 10);






INSERT INTO BookAuthor(BookID, AuthorID)
VALUES
(N'A1.NA.2004', 2),
(N'A1.NA.2004', 3),
(N'A1.NA.2004', 4),
(N'A2.LT.2001', 5),
(N'A3.LH.2003', 6),
(N'A4.MH.1991', 7),
(N'B1.DT.1997', 8),
(N'B1.DT.1997', 9),
(N'B2.NT.1991', 10),
(N'B3.NT.1991', 10),
(N'B4.NT.1991', 10),
(N'B5.BH.2013', 11),
(N'B5.BH.2013', 12),
(N'C1.PT.2003', 13),
(N'C1.PT.2003', 14),
(N'C2.VN.2000', 15),
(N'C3.TH.2002', 16),
(N'C4.LL.2010', 17),
(N'D1.NC.2016', 18),
(N'D1.NC.2016', 19),
(N'D2.CT.2000', 20),
(N'D2.CT.2000', 21),
(N'D2.CT.2000', 22),
(N'D2.CT.2000', 23),
(N'D2.CT.2000', 24),
(N'D3.NN.2012', 25),
(N'D4.U5.2007', 1),
(N'D5.KS.2008', 26),
(N'E1.NH.2015', 27),
(N'E2.VD.2008', 28),
(N'E3.DN.1994', 29),
(N'E3.DN.1994', 30),
(N'E3.DN.1994', 27),
(N'E4.NT.2017', 31),
(N'E4.NT.2017', 32),
(N'E5.RH.1964', 33),
(N'F1.JE.2003', 34),
(N'F2.KB.2008', 35),
(N'F3.CI.2014', 36),
(N'F4.PJ.2007', 37),
(N'F5.AG.2018', 38),
(N'G1.ND.2003', 39),
(N'G2.CK.2006', 40),
(N'G3.NL.2011', 41),
(N'H1.GG.2012', 42),
(N'H2.U5.1979', 1),
(N'H3.JB.1996', 43),
(N'J1.DN.2009', 44),
(N'J2.DN.2005', 44),
(N'J3.TT.2015', 45),
(N'K1.MQ.2014', 46),
(N'K2.VQ.2014', 47),
(N'K3.U5.2005', 1);






INSERT INTO BookCopy(BookID, BookCode, BookLocation, BookStatus)
VALUES
(N'A1.NA.2004', 'A1.NA.2004', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A1.NA.2004', 'A1.NA.2004.C2', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A2.LT.2001', 'A2.LT.2001', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A2.LT.2001', 'A2.LT.2001.C2', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A2.LT.2001', 'A2.LT.2001.C3', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A2.LT.2001', 'A2.LT.2001.C4', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'A3.LH.2003', 'A3.LH.2003', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'A3.LH.2003', 'A3.LH.2003.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'A4.MH.1991', 'A4.MH.1991', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'A4.MH.1991', 'A4.MH.1991.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'A4.MH.1991', 'A4.MH.1991.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B1.DT.1997', 'B1.DT.1997', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B2.NT.1991', 'B2.NT.1991', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B2.NT.1991', 'B2.NT.1991.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B3.NT.1991', 'B3.NT.1991', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B3.NT.1991', 'B3.NT.1991.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B4.NT.1991', 'B4.NT.1991', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B4.NT.1991', 'B4.NT.1991.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'B5.BH.2013', 'B5.BH.2013', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C1.PT.2003', 'C1.PT.2003', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C1.PT.2003', 'C1.PT.2003.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C1.PT.2003', 'C1.PT.2003.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C2.VN.2000', 'C2.VN.2000', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C3.TH.2002', 'C3.TH.2002', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C3.TH.2002', 'C3.TH.2002.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'C4.LL.2010', 'C4.LL.2010', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D1.NC.2016', 'D1.NC.2016', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D2.CT.2000', 'D2.CT.2000', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C4', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C5', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C6', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C7', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C8', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C9', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D3.NN.2012', 'D3.NN.2012.C10', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D4.U5.2007', 'D4.U5.2007', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D4.U5.2007', 'D4.U5.2007.C2', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'D5.KS.2008', 'D5.KS.2008', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E1.NH.2015', 'E1.NH.2015', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E2.VD.2008', 'E2.VD.2008', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E2.VD.2008', 'E2.VD.2008.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E2.VD.2008', 'E2.VD.2008.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E3.DN.1994', 'E3.DN.1994', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E4.NT.2017', 'E4.NT.2017', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'E5.RH.1964', 'E5.RH.1964', N'Phòng lưu trữ (P.124)', 'Available'),
(N'F1.JE.2003', 'F1.JE.2003', N'Phòng lưu trữ (P.124)', 'Available'),
(N'F2.KB.2008', 'F2.KB.2008', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'F3.CI.2014', 'F3.CI.2014', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'F4.PJ.2007', 'F4.PJ.2007', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'F4.PJ.2007', 'F4.PJ.2007.C2', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'F5.AG.2018', 'F5.AG.2018', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'G1.ND.2003', 'G1.ND.2003', N'Phòng đọc Luận văn - Luận án (P.313)', 'Available'),
(N'G2.CK.2006', 'G2.CK.2006', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'G2.CK.2006', 'G2.CK.2006.C2', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'G3.NL.2011', 'G3.NL.2011', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'H1.GG.2012', 'H1.GG.2012', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'H2.U5.1979', 'H2.U5.1979', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'H3.JB.1996', 'H3.JB.1996', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'J1.DN.2009', 'J1.DN.2009', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'J2.DN.2005', 'J2.DN.2005', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'J2.DN.2005', 'J2.DN.2005.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'J2.DN.2005', 'J2.DN.2005.C3', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'J2.DN.2005', 'J2.DN.2005.C4', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'J2.DN.2005', 'J2.DN.2005.C5', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'J3.TT.2015', 'J3.TT.2015', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C4', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C5', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C6', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C7', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C8', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C9', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K1.MQ.2014', 'K1.MQ.2014.C10', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K2.VQ.2014', 'K2.VQ.2014', N'Phòng mượn Giáo trình (P.111)', 'Available'),
(N'K2.VQ.2014', 'K2.VQ.2014.C2', N'Phòng mượn Giáo trình (P.111)', 'Available'),
(N'K2.VQ.2014', 'K2.VQ.2014.C3', N'Phòng mượn Giáo trình (P.111)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C2', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C3', N'Phòng đọc chuyên ngành A-P (P.402)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C4', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C5', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C6', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C7', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C8', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C9', N'Phòng mượn sách tham khảo (P.102)', 'Available'),
(N'K3.U5.2005', 'K3.U5.2005.C10', N'Phòng mượn sách tham khảo (P.102)', 'Available');







INSERT INTO ReaderCardID(ReaderCardID, CardPIN, CardStatus, DateIssued, DateExpired)
VALUES
('20218217', '082024', 'Active', '2021-09-17', '2028-09-17'),
('20218218', '082024', 'Active', '2021-09-17', '2028-09-17'),
('20218219', '082024', 'Active', '2021-09-17', '2028-09-17'),
('20218220', '082024', 'Active', '2021-09-17', '2028-09-17'),
('20218221', '082024', 'Active', '2021-09-17', '2028-09-17'),
('20218222', '082024', 'Active', '2021-09-17', '2028-09-17'),
('DG1', '082024', 'Active', '2024-06-17', '2025-06-17'),
('DG2', '082024', 'Active', '2024-02-21', '2024-11-21'),
('DG3', '082024', 'Active', '2023-11-09', '2024-11-09'),
('DG4', '082024', 'Active', '2021-07-22', '2024-10-22'),
('DG5', '082024', 'Active', '2021-09-17', '2026-09-17'),
('DG6', '082024', 'Expired', '2023-11-17', '2024-02-17'),
('DG7', '082024', 'Expired', '2021-09-17', '2022-09-17'),
('DG8', '082024', 'Active', '2021-09-17', '2028-09-17'),
('DG9', '082024', 'Active', '2021-09-17', '2028-09-17');

INSERT INTO Reader(ReaderName, ReaderDOB, ReaderAddress, ReaderGender, ReaderPhoneNumber, ReaderEmail, ReaderIdentificationNumber, ReaderCardID, ReaderPassword)
VALUES
('Vo Duc Thang', '2003-09-17', 'Kyoto, Japan', 'Male', '0987654321', 'vdt20218217@sis.hust.edu', '040203001920', '20218217', '762412471846146737986207318625420722243'),
('Nguyen Van A', '2000-01-01', 'Hanoi', 'Male', '0987654320', 'nva20218218@sis.hust.edu', '040203001919', '20218218', '762412471846146737986207318625420722243'),
('Tran Thi B', '2000-01-02', 'Hai Duong', 'Female', '0987654319', 'ttb20218219@sis.hust.edu', '040203001918', '20218219', '762412471846146737986207318625420722243'),
('Le Van C', '2000-01-03', 'Hai Phong', 'Male', '0987654318', 'lvc20218220@sis.hust.edu', '040203001917', '20218220', '762412471846146737986207318625420722243'),
('Pham Thi D', '2000-01-04', 'Da Nang', 'Female', '0987654317', 'ptd20218221@sis.hust.edu', '040203001916', '20218221', '762412471846146737986207318625420722243'),
('Nguyen Van E', '2000-01-05', 'Ho Chi Minh City', 'Male', '0987654316', 'nve20218222@sis.hust.edu', '040203001915', '20218222', '762412471846146737986207318625420722243'),
('Tran Thi F', '2000-01-06', 'Can Tho', 'Female', '0987654315', 'ttf2000@gmail.com', '040203001914', 'DG1', '15410012614513998108181121232209311017111612'),
('Le Van G', '2000-01-07', 'Vung Tau', 'Male', '0987654314', 'lvg2000@gmail.com', '040203001913', 'DG2', '15410012614513998108181121232209311017111612'),
('Pham Thi H', '2000-01-08', 'Nha Trang', 'Female', '0987654313', 'pth2000@gmail.com', '040203001912', 'DG3', '15410012614513998108181121232209311017111612'),
('Nguyen Van I', '2000-01-09', 'Da Lat', 'Male', '0987654312', 'nvi2000@gmail.com', '040203001911', 'DG4', '15410012614513998108181121232209311017111612'),
('Tran Thi J', '2000-01-10', 'Phan Thiet', 'Female', '0987654311', 'ttj2000@gmail.com', '040203001910', 'DG5', '15410012614513998108181121232209311017111612'),
('Le Van K', '2000-01-11', 'Quy Nhon', 'Female', '0987654310', 'lvk2000@gmail.com', '040203001909', 'DG6', '15410012614513998108181121232209311017111612'),
('Pham Thi L', '2000-01-12', 'Buon Ma Thuot', 'Female', '0987654309', 'ptl2000@gmail.com', '040203001908', 'DG7', '15410012614513998108181121232209311017111612'),
('Nguyen Van M', '2000-01-13', 'Hanoi', 'Male', '0987654308', 'nvm2000@gmail.com', '040203001907', 'DG8', '15410012614513998108181121232209311017111612'),
('Tran Thi N', '2000-01-14', 'Hai Duong', 'Female', '0987654307', 'ttn2000@gmail.com', '040203001906', 'DG9', '15410012614513998108181121232209311017111612');

INSERT INTO YourBookshelf(ReaderCardID)
VALUES 
('20218217'),
('20218218'),
('20218219'),
('20218220'),
('20218221'),
('20218222'),
('DG1'),
('DG2'),
('DG3'),
('DG4'),
('DG5'),
('DG6'),
('DG7'),
('DG8'),
('DG9');

INSERT INTO Librarian(LibrarianName, LibrarianDOB, LibrarianAddress, LibrarianGender, LibrarianPhoneNumber, LibrarianEmail, LibrarianIdentificationNumber, LibrarianPassword, LibrarianDepartment, LibrarianPosition, LibrarianWorkplace)
VALUES
(N'Đinh Văn Hải', '1990-01-01', 'Hanoi', 'Male', '0987654322', 'hai.dinhvan@hust.libraryTQB.vn', '040203001921', '216125107150691248917719582916031223229252', 'BOARD OF DIRECTORS', 'Director', 'P.321'),
(N'Lê Thị Quyên', '1990-01-02', 'Hai Duong', 'Female', '0987654323', 'quyen.lethi@hust.libraryTQB.vn', '040203001922', '216125107150691248917719582916031223229252', 'BOARD OF DIRECTORS', 'Deputy Director', 'P.209'),
(N'Hà Thị Huệ', '1990-01-03', 'Hai Phong', 'Female', '0987654324', 'hue.hathi@hust.libraryTQB.vn', '040203001923', '216125107150691248917719582916031223229252', 'OFFICE', 'Head of Department', 'P.208'),
(N'Mạc Thị Bích Châm', '1990-01-04', 'Da Nang', 'Female', '0987654325', 'cham.macthibich@hust.libraryTQB.vn', '040203001924', '216125107150691248917719582916031223229252', 'OFFICE', 'Deputy Director', 'P.204'),
(N'Nguyễn Thị Minh Châm', '1990-01-05', 'Ho Chi Minh City', 'Female', '0987654326', 'ncham.nguyenthiminh@hust.libraryTQB.vn', '040203001925', '216125107150691248917719582916031223229252', 'OFFICE', 'Staff', 'P.204'),
(N'Lê Hải Thanh', '1990-01-06', 'Can Tho', 'Male', '0987654327', 'thanh.lehai@hust.libraryTQB.vn', '040203001926', '216125107150691248917719582916031223229252', 'OFFICE', 'Staff', 'P.321'),
(N'Cung Thị Bích Hà', '1990-01-07', 'Vung Tau', 'Female', '0987654328', 'ha.cungthibich@hust.libraryTQB.vn', '040203001927', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Head of Department', 'P.404'),
(N'Nguyễn Hương Trà', '1990-01-08', 'Nha Trang', 'Female', '0987654329', 'tra.nguyenhuong@hust.libraryTQB.vn', '040203001928', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Deputy Head of Department', 'P.404'),
(N'Nguyễn Mai Chi', '1990-01-09', 'Hanoi', 'Female', '0987654330', 'chi.nguyenmai1@hust.libraryTQB.vn', '040203001929', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.404'),
(N'Đào Thị Linh', '1990-01-10', 'Hai Phong', 'Female', '0987654331', 'linh.daothi@hust.libraryTQB.vn', '040203001930', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.404'),
(N'Nguyễn Thanh Hùng', '1990-01-11', 'Hai Duong', 'Male', '0987654332', 'hung.nguyenthanh1@hust.libraryTQB.vn', '040203001931', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.404'),
(N'Đào Linh Chi', '1990-01-12', 'Da Nang', 'Female', '0987654333', 'chi.daolinh@hust.libraryTQB.vn', '040203001932', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.404'),
(N'Vũ Thị Hoa', '1990-01-13', 'Hanoi', 'Female', '0987654334', 'hoa.vuthi@hust.libraryTQB.vn', '040203001933', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.102'),
(N'Nguyễn Thị Nga', '1990-01-14', 'Hanoi', 'Female', '0987654335', 'nga.nguyenthi1@hust.libraryTQB.vn', '040203001934', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.102'),
(N'Nguyễn Ngọc Sơn', '1990-01-15', 'Hanoi', 'Male', '0987654336', 'son.nguyenngoc@hust.libraryTQB.vn', '040203001935', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.111'),
(N'Phạm Mỹ Hạnh', '1990-01-16', 'Hanoi', 'Female', '0987654337', 'hanh.phammy@hust.libraryTQB.vn', '040203001936', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.111'),
(N'Tạ Thị Huyền Nhung', '1990-01-17', 'Hung Yen', 'Female', '0987654338', 'nhung.tathihuyen@hust.libraryTQB.vn', '040203001937', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.111'),
(N'Phạm Thị Hải Yến', '1990-01-18', 'Hanoi', 'Female', '0987654339', 'yen.phamthihai@hust.libraryTQB.vn', '040203001938', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.111'),
(N'Thái Thị Trầm', '1990-01-19', 'Hai Phong', 'Female', '0987654340', 'tram.thaithi@hust.libraryTQB.vn', '040203001939', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.313'),
(N'Trần Vân Anh', '1990-01-20', 'Nghe An', 'Female', '0987654341', 'anh.tranvan1@hust.libraryTQB.vn', '040203001940', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.402'),
(N'Nguyễn Văn Hải', '1990-01-21', 'Ha Tinh', 'Male', '0987654342', 'hai.nguyenvan@hust.libraryTQB.vn', '040203001941', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.411'),
(N'Phạm Thị Thu Hà', '1990-01-22', 'Nghe An', 'Female', '0987654343', 'ha.phamthithu1@hust.libraryTQB.vn', '040203001942', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.419'),
(N'Nguyễn Thị Thu Huyền', '1990-01-23', 'Hanoi', 'Female', '0987654344', 'huyen.nguyenthithu1@hust.libraryTQB.vn', '040203001943', '216125107150691248917719582916031223229252', 'STUDY AND RESEARCH SUPPORT SERVICES', 'Staff', 'P.526'),
(N'Phạm Thị Lan', '1990-01-24', 'Hai Duong', 'Female', '0987654345', 'lan.phamthi@hust.libraryTQB.vn', '040203001944', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Head of Department', 'P.304A'),
(N'Nguyễn Thị Ngân', '1990-01-25', 'Bac Ninh', 'Female', '0987654346', 'ngan.nguyenthi@hust.libraryTQB.vn', '040203001945', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Deputy Head of Department', 'P.304A'),
(N'Nguyễn Anh Tuấn', '1990-01-26', 'Hoa Binh', 'Male', '0987654347', 'tuan.nguyenanh1@hust.libraryTQB.vn', '040203001946', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Staff', 'P.304A'),
(N'Phạm Thị Ngọc Yến', '1990-01-27', 'Hai Phong', 'Female', '0987654348', 'yen.phamthingoc1@hust.libraryTQB.vn', '040203001947', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Staff', 'P.304A'),
(N'Lê Hữu Việt', '1990-01-28', 'Hanoi', 'Male', '0987654349', 'viet.lehuu@hust.libraryTQB.vn', '040203001948', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Staff', 'P.304A'),
(N'Nguyễn Thị Thuỷ', '1990-01-29', 'Dien Bien', 'Female', '0987654350', 'thuy.nguyenthi2@hust.libraryTQB.vn', '040203001949', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Staff', 'P.304A'),
(N'Nguyễn Thị Thu Thuỷ', '1990-01-30', 'Hanoi', 'Female', '0987654351', 'thuy.nguyenthithu1@hust.libraryTQB.vn', '040203001950', '216125107150691248917719582916031223229252', 'MANAGEMENT AND DEVELOPMENT OF INFORMATION RESOURCES', 'Staff', 'P.304A'),
(N'Hồ Thị Lợi', '1990-01-31', 'Hai Duong', 'Female', '0987654352', 'loi.hothi@hust.libraryTQB.vn', '040203001951', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Head of Department', 'P.304B'),
(N'Trần Thị Tuyến', '1990-02-01', 'Hung Yen', 'Female', '0987654353', 'tuyen.tranthi@hust.libraryTQB.vn', '040203001952', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Deputy Head of Department', 'P.304B'),
(N'Bùi Thị Thanh', '1990-02-02', 'Hanoi', 'Female', '0987654354', 'thanh.buithi@hust.libraryTQB.vn', '040203001953', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Staff', 'P.304B'),
(N'Hoàng Ngọc Chi', '1990-02-03', 'Nghe An', 'Female', '0987654355', 'chi.hoangngoc@hust.libraryTQB.vn', '040203001954', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Staff', 'P.304B'),
(N'Trần Huyền Trang', '1990-02-04', 'Hai Duong', 'Female', '0987654356', 'trang.tranhuyen@hust.libraryTQB.vn', '040203001955', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Staff', 'P.304B'),
(N'Nguyễn Hoàng Thái', '1990-02-05', 'Hanoi', 'Male', '0987654357', 'thai.nguyenhoang@hust.libraryTQB.vn', '040203001956', '216125107150691248917719582916031223229252', 'DIGITAL INFORMATION SYSTEMS MANAGEMENT', 'Staff', 'P.304B'),
(N'Nguyễn Kim Chi', '1990-02-06', 'Hai Phong', 'Female', '0987654358', 'chi.nguyenkim@hust.libraryTQB.vn', '040203001957', '216125107150691248917719582916031223229252', 'BRAND MANAGEMENT', 'Head of Department', 'P.321'),
(N'Nguyễn Diệu Ngọc', '1990-02-07', 'Ninh Binh', 'Female', '0987654359', 'ngoc.nguyendieu@hust.libraryTQB.vn', '040203001958', '216125107150691248917719582916031223229252', 'MEDIA', 'Head of Department', 'P.321'),
(N'Trần Thu Trang', '1990-02-08', 'Hanoi', 'Female', '0987654360', 'trang.tranthu@hust.libraryTQB.vn', '040203001959', '216125107150691248917719582916031223229252', 'MEDIA', 'Staff', 'P.321'),
(N'Nguyễn Phương Thảo', '1990-02-09', 'Hai Duong', 'Female', '0987654361', 'thao.nguyenphuong@hust.libraryTQB.vn', '040203001960', '216125107150691248917719582916031223229252', 'MEDIA', 'Staff', 'P.321'),
(N'Đinh Văn Phong', '1990-02-10', 'Nam Dinh', 'Male', '0987654362', 'phong.dinhvan@hust.libraryTQB.vn', '040203001961', '216125107150691248917719582916031223229252', 'SCIENCE AND TECHNOLOGY JOURNAL', 'Deputy Editor-in-Chief', 'C1-224'),
(N'Nguyễn Thu Hà', '1990-02-11', 'Hanoi', 'Female', '0987654363', 'ha.nguyenthu1@hust.libraryTQB.vn', '040203001962', '216125107150691248917719582916031223229252', 'SCIENCE AND TECHNOLOGY JOURNAL', 'Staff', 'P.321'),
(N'Trần Thị Thanh Thuỷ', '1990-02-12', 'Nghe An', 'Female', '0987654364', 'thuy.tranthithanh1@hust.libraryTQB.vn', '040203001963', '216125107150691248917719582916031223229252', 'SCIENCE AND TECHNOLOGY JOURNAL', 'Staff', 'C1-224');





INSERT INTO BookRequest(ListBookCode, ReaderCardID, RequestStatus, DateBorrowed, DateDue, DateReturned, PenaltyFee, RequestNotes)
VALUES
('J2.DN.2005.C4, J2.DN.2005.C5', '20218217', 'Returned', '2024-05-17', '2024-07-17', '2024-07-24', 20000, 'Overdue return'),
('J3.TT.2015.C2', '20218218', 'Returned', '2024-06-11', '2024-08-11', '2024-07-24', 100000, 'Lost J3.TT.2015.C2'),
('K2.VQ.2014, K2.VQ.2014.C2, K2.VQ.2014.C3', '20218220', 'Returned', '2024-06-05', '2024-08-05', '2024-07-24', 0, 'No notes'),
('K3.U5.2005, K3.U5.2005.C2, K3.U5.2005.C3, K3.U5.2005.C4, K3.U5.2005.C5, K3.U5.2005.C6, K3.U5.2005.C7, K3.U5.2005.C8, K3.U5.2005.C9, K3.U5.2005.C10', '20218221', 'Borrowed', '2024-07-21', '2024-09-21', NULL, 0, 'No notes'),
('K2.VQ.2014, K2.VQ.2014.C2, K2.VQ.2014.C3', '20218222', 'Borrowed', '2024-07-24', '2024-08-24', NULL, 0, 'No notes'),
('K1.MQ.2014, K1.MQ.2014.C2', 'DG2', 'Requested', NULL, NULL, NULL, 0, 'No notes'),
('A1.NA.2004, A2.LT.2001.C2', 'DG3', 'Requested', NULL, NULL, NULL, 0, 'No notes');






GO


CREATE PROCEDURE USP_LoginLibrarian
    @LibrarianPhoneNumber NVARCHAR(50),
    @LibrarianPassword NVARCHAR(500)
AS 
BEGIN
    SELECT 
        LibrarianPhoneNumber, LibrarianPassword
    FROM 
        Librarian
    WHERE 
        LibrarianPhoneNumber = @LibrarianPhoneNumber AND LibrarianPassword = @LibrarianPassword
END
GO

CREATE PROCEDURE USP_LoginReader
    @ReaderCardID NVARCHAR(50),
    @ReaderPassword NVARCHAR(500)
AS
BEGIN
    SELECT 
        ReaderCardID, ReaderPassword
    FROM 
        Reader
    WHERE 
        ReaderCardID = @ReaderCardID AND ReaderPassword = @ReaderPassword
END
GO

CREATE PROCEDURE USP_LoadTableBook
AS
BEGIN
SELECT
	b.BookTitle AS Title,
    b.BookID AS ID,
	b.BookCategory AS 'Category',
    STRING_AGG(a.AuthorName, ', ') AS 'Author Name',
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
GROUP BY
    b.BookID, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity
END
GO


CREATE PROCEDURE USP_LoadTableBookCopy
AS
BEGIN
SELECT
    b.BookTitle AS Title,
    b.BookID AS ID,
    bc.BookCode AS 'Code',
    bc.BookLocation AS 'Location',
    bc.BookStatus AS 'Status',
    b.BookCategory AS 'Category',
    STRING_AGG(a.AuthorName, ', ') AS 'Author Name',
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
LEFT JOIN
    BookCopy bc ON b.BookID = bc.BookID
GROUP BY
    b.BookID, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, 
    b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity, 
    bc.BookCode, bc.BookLocation, bc.BookStatus
END
GO


CREATE PROCEDURE USP_LoadTableLibrarian
AS
BEGIN
SELECT
    LibrarianName AS Name,
    LibrarianDOB AS DOB,
    LibrarianAddress AS Address,
    LibrarianGender AS Gender,
    LibrarianPhoneNumber AS 'Phone Number',
    LibrarianEmail AS Email,
    LibrarianIdentificationNumber AS 'Identification Number',
    LibrarianDepartment AS Department,
    LibrarianPosition AS Position,
    LibrarianWorkplace AS Workplace
FROM
    Librarian
END
GO


CREATE PROCEDURE USP_LoadTableReader
AS
BEGIN
SELECT
    ReaderName AS Name,
    ReaderDOB AS DOB,
    ReaderAddress AS Address,
    ReaderGender AS Gender,
    ReaderPhoneNumber AS 'Phone Number',
    ReaderEmail AS Email,
    ReaderIdentificationNumber AS 'Identification Number',
    ReaderCardID AS 'Card ID'
FROM
    Reader
END
GO


CREATE PROCEDURE USP_LoadTableBookRequest
AS
BEGIN
SELECT
    br.BookRequestID AS RequestID,
    br.ListBookCode AS BookCode,
    br.ReaderCardID AS CardID,
    br.RequestStatus AS Status,
    br.DateBorrowed AS BorrowDate,
    br.DateDue AS DueDate,
    br.DateReturned AS ReturnDate,
    br.PenaltyFee AS PenaltyFee,
    br.RequestNotes AS Note
FROM
    BookRequest br
END
GO



CREATE PROCEDURE USP_LoadTableReaderCardID
AS
BEGIN
SELECT
    r.ReaderName AS Name,
    r.ReaderIdentificationNumber AS 'ID Number',
    rc.ReaderCardID AS 'Card ID',
    rc.CardStatus AS 'Status',
    rc.DateIssued AS 'Date Issued',
    rc.DateExpired AS 'Date Expired'
FROM
    Reader r
LEFT JOIN
    ReaderCardID rc ON r.ReaderCardID = rc.ReaderCardID
END
GO


CREATE PROCEDURE USP_UpdateBook
    @BookID NVARCHAR(50),
    @BookTitle NVARCHAR(500),
    @BookCategory NVARCHAR(10),
    @YearPublished INT,
    @BookPublisher NVARCHAR(500),
    @BookTopic NVARCHAR(200),
    @BookDescription NVARCHAR(500),
    @BookNotes NVARCHAR(1000),
    @BookQuantity INT,
    @AuthorName NVARCHAR(500)
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        -- Cập nhật thông tin sách
        UPDATE Book
        SET 
            BookTitle = @BookTitle,
            BookCategory = @BookCategory,
            YearPublished = @YearPublished,
            BookPublisher = @BookPublisher,
            BookTopic = @BookTopic,
            BookDescription = @BookDescription,
            BookNotes = @BookNotes,
            BookQuantity = @BookQuantity
        WHERE BookID = @BookID;

        -- Kiểm tra xem tác giả đã tồn tại chưa
        DECLARE @AuthorID INT;
        SELECT @AuthorID = AuthorID FROM Author WHERE AuthorName = @AuthorName;

        -- Nếu tác giả không tồn tại, thêm mới tác giả
        IF @AuthorID IS NULL
        BEGIN
            INSERT INTO Author (AuthorName)
            VALUES (@AuthorName);

            SET @AuthorID = SCOPE_IDENTITY();
        END

        -- Cập nhật bảng BookAuthor
        -- Xóa các bản ghi cũ trong BookAuthor
        DELETE FROM BookAuthor WHERE BookID = @BookID;

        -- Thêm bản ghi mới vào BookAuthor
        INSERT INTO BookAuthor (BookID, AuthorID)
        VALUES (@BookID, @AuthorID);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
GO


-- Sau khi insert thì kiểm tra xem quantity là bao nhiêu rồi thêm vào bảng BookCopy số lượng bằng với quantity. 
-- BookCode của BookCopy sẽ được tạo tự động theo quy tắc BookID, BookID.C2, BookID.C3, ... (ví dụ: E3.DN.1994, E3.DN.1994.C2, E3.DN.1994.C3, ...)
CREATE PROCEDURE USP_InsertBook
    @BookID NVARCHAR(50),
    @BookTitle NVARCHAR(500),
    @BookCategory NVARCHAR(10),
    @YearPublished INT,
    @BookPublisher NVARCHAR(500),
    @BookTopic NVARCHAR(200),
    @BookDescription NVARCHAR(500),
    @BookNotes NVARCHAR(1000),
    @BookQuantity INT,
    @AuthorName NVARCHAR(500)
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        -- Chèn thông tin sách vào bảng Book
        INSERT INTO Book (BookID, BookTitle, BookCategory, YearPublished, BookPublisher, BookTopic, BookDescription, BookNotes, BookQuantity)
        VALUES (@BookID, @BookTitle, @BookCategory, @YearPublished, @BookPublisher, @BookTopic, @BookDescription, @BookNotes, @BookQuantity);

        -- Kiểm tra xem tác giả đã tồn tại chưa
        DECLARE @AuthorID INT;
        SELECT @AuthorID = AuthorID FROM Author WHERE AuthorName = @AuthorName;

        -- Nếu tác giả không tồn tại, thêm mới tác giả
        IF @AuthorID IS NULL
        BEGIN
            INSERT INTO Author (AuthorName)
            VALUES (@AuthorName);

            SET @AuthorID = SCOPE_IDENTITY();
        END

        -- Thêm bản ghi vào bảng BookAuthor
        INSERT INTO BookAuthor (BookID, AuthorID)
        VALUES (@BookID, @AuthorID);

        -- Thêm bản ghi vào bảng BookCopy với số lượng bằng với BookQuantity
        DECLARE @i INT = 1;
        DECLARE @BookCode NVARCHAR(255);

        WHILE @i <= @BookQuantity
        BEGIN
            IF @i = 1
                SET @BookCode = @BookID;
            ELSE
                SET @BookCode = CONCAT(@BookID, '.C', @i);

            INSERT INTO BookCopy (BookID, BookCode, BookLocation, BookStatus)
            VALUES (@BookID, @BookCode, 'Location not yet specified', 'Not Available');

            SET @i = @i + 1;
        END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
GO

CREATE PROCEDURE USP_UpdateLibrarian
    @LibrarianName NVARCHAR(100),
    @LibrarianDOB DATE,
    @LibrarianAddress NVARCHAR(100),
    @LibrarianGender NVARCHAR(10),
    @LibrarianPhoneNumber NVARCHAR(50),
    @LibrarianEmail NVARCHAR(100),
    @LibrarianIdentificationNumber NVARCHAR(50),
    @LibrarianDepartment NVARCHAR(150),
    @LibrarianPosition NVARCHAR(150),
    @LibrarianWorkplace NVARCHAR(150)
AS
BEGIN
    BEGIN TRANSACTION;
    BEGIN TRY
        UPDATE Librarian
        SET
            LibrarianName = @LibrarianName,
            LibrarianDOB = @LibrarianDOB,
            LibrarianAddress = @LibrarianAddress,
            LibrarianGender = @LibrarianGender,
            LibrarianPhoneNumber = @LibrarianPhoneNumber,
            LibrarianEmail = @LibrarianEmail,
            LibrarianIdentificationNumber = @LibrarianIdentificationNumber,
            LibrarianDepartment = @LibrarianDepartment,
            LibrarianPosition = @LibrarianPosition,
            LibrarianWorkplace = @LibrarianWorkplace
        WHERE LibrarianPhoneNumber = @LibrarianPhoneNumber;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
GO

CREATE PROCEDURE USP_InsertLibrarian
    @LibrarianName NVARCHAR(100),
    @LibrarianDOB DATE,
    @LibrarianAddress NVARCHAR(100),
    @LibrarianGender NVARCHAR(10),
    @LibrarianPhoneNumber NVARCHAR(50),
    @LibrarianEmail NVARCHAR(100),
    @LibrarianIdentificationNumber NVARCHAR(50),
    @LibrarianDepartment NVARCHAR(150),
    @LibrarianPosition NVARCHAR(150),
    @LibrarianWorkplace NVARCHAR(150)
AS
BEGIN
    BEGIN TRANSACTION;
    BEGIN TRY
        INSERT INTO Librarian (LibrarianName, LibrarianDOB, LibrarianAddress, LibrarianGender, LibrarianPhoneNumber, LibrarianEmail, LibrarianIdentificationNumber, LibrarianDepartment, LibrarianPosition, LibrarianWorkplace)
        VALUES (@LibrarianName, @LibrarianDOB, @LibrarianAddress, @LibrarianGender, @LibrarianPhoneNumber, @LibrarianEmail, @LibrarianIdentificationNumber, @LibrarianDepartment, @LibrarianPosition, @LibrarianWorkplace);
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
GO

-- Hàm USP_AddABookCopy để thêm một bản sao sách vào bảng BookCopy. Lấy ra Book Code có dạng BookID.Cx, trong đó x là số thứ tự của bản sao sách (x = 2, 3, 4, ...).
-- Nếu quantity của BookID là 1 thì BookCode của nó là BookID => thêm bản sao BookID.C2 vào bảng BookCopy.
-- Nếu quantity của BookID lớn hơn 1 thì BookCode của nó là BookID.Cx, trong đó x là số thứ tự của bản sao sách (x = 2, 3, 4, ...).
-- Hàm này nhận vào một tham số @BookID kiểu NVARCHAR(50) là mã sách cần thêm bản sao.
-- Thêm một bản vào dbo.BookCopy với BookID = @BookID, BookCode = BookID.Cx, BookLocation = 'Location not yet specified', BookStatus = 'Not Available'.
-- Sửa quantity của sách trong bảng Book tăng thêm 1.

CREATE PROCEDURE USP_AddABookCopy
    @BookID NVARCHAR(50)
AS
BEGIN
    DECLARE @NewBookCode NVARCHAR(100);
    DECLARE @CopyCount INT;
    DECLARE @CurrentQuantity INT;

    -- Get the current quantity for the given BookID
    SELECT @CurrentQuantity = BookQuantity
    FROM dbo.Book
    WHERE BookID = @BookID;

    -- Get the count of existing copies for the given BookID
    SELECT @CopyCount = COUNT(*)
    FROM dbo.BookCopy
    WHERE BookID = @BookID;

    -- Generate the new BookCode
    IF @CurrentQuantity = 1
    BEGIN
        SET @NewBookCode = @BookID + '.C2';
    END
    ELSE
    BEGIN
        SET @NewBookCode = @BookID + '.C' + CAST(@CopyCount + 1 AS NVARCHAR(10));
    END

    -- Insert the new book copy
    INSERT INTO dbo.BookCopy (BookID, BookCode, BookLocation, BookStatus)
    VALUES (@BookID, @NewBookCode, 'Location not yet specified', 'Not Available');

    -- Update the quantity of the book in the Book table
    UPDATE dbo.Book
    SET BookQuantity = BookQuantity + 1
    WHERE BookID = @BookID;
END;
GO

-- Hàm USP_DeleteBook để xóa một sách khỏi hệ thống. Khi xóa một sách, tất cả các bản sao của sách đó cũng sẽ bị xóa và tác giả của sách đó cũng sẽ bị xóa khỏi bảng BookAuthor.
-- Nếu có một bản sao sách đó đã được mượn hoặc đang được yêu cầu mượn, không thể xóa sách đó.
-- Hàm này nhận vào một tham số @BookID kiểu NVARCHAR(50) là mã sách cần xóa.
CREATE PROCEDURE USP_DeleteBook
    @BookID NVARCHAR(50)
AS
BEGIN
    DECLARE @BorrowedOrRequestedCount INT;

    -- Check if any copies of the book are borrowed or requested
    SELECT @BorrowedOrRequestedCount = COUNT(*)
    FROM dbo.BookCopy
    WHERE BookID = @BookID AND (BookStatus = 'Borrowed' OR BookStatus = 'Requested');

    -- If there are borrowed or requested copies, return an error message
    IF @BorrowedOrRequestedCount > 0
    BEGIN
        RAISERROR('Cannot delete the book because one or more copies are borrowed or requested.', 16, 1);
        RETURN;
    END

    -- Delete all copies of the book from the BookCopy table
    DELETE FROM dbo.BookCopy
    WHERE BookID = @BookID;

    -- Delete the authors of the book from the BookAuthor table
    DELETE FROM dbo.BookAuthor
    WHERE BookID = @BookID;

    -- Delete the book from the Book table
    DELETE FROM dbo.Book
    WHERE BookID = @BookID;
END;
GO


-- Hàm USP_DecreaseBookQuantity để giảm số lượng sách trong hệ thống. 
-- Nếu số lượng sách giảm về 0 thì xóa luôn sách đó khỏi hệ thống.
-- Rồi xóa tác giả của sách đó khỏi bảng BookAuthor
CREATE PROCEDURE USP_DecreaseBookQuantity 
    @BookID NVARCHAR(50)
AS
BEGIN
    DECLARE @CurrentQuantity INT;

    -- Get the current quantity of the book
    SELECT @CurrentQuantity = BookQuantity
    FROM dbo.Book
    WHERE BookID = @BookID;

    -- Check the current quantity
    IF @CurrentQuantity > 1
    BEGIN
        -- Decrease the quantity by 1
        UPDATE dbo.Book
        SET BookQuantity = BookQuantity - 1
        WHERE BookID = @BookID;
    END
    ELSE IF @CurrentQuantity = 1
    BEGIN
        -- Delete all copies of the book from the BookCopy table
        DELETE FROM dbo.BookCopy
        WHERE BookID = @BookID;

		-- Delete the authors of the book from the BookAuthor table
        DELETE FROM dbo.BookAuthor
        WHERE BookID = @BookID;

        -- Delete the book from the Book table
        DELETE FROM dbo.Book
        WHERE BookID = @BookID;
    END
END;
GO


-- Hàm USP_DeleteReader để xóa một độc giả khỏi hệ thống. Khi xóa một độc giả, bookshelf của độc giả đó cũng sẽ bị xóa, reader card của độc giả đó cũng sẽ bị xóa.
-- Sẽ có trường hợp độc giả không có bookshelf
CREATE PROCEDURE USP_DeleteReader
    @ReaderCardID NVARCHAR(50)
AS
BEGIN
    -- Delete the reader's bookshelf entries
    IF EXISTS (SELECT 1 FROM YourBookshelf WHERE ReaderCardID = @ReaderCardID)
    BEGIN
        -- Delete the bookshelf of the reader
        DELETE FROM YourBookshelf
        WHERE ReaderCardID = @ReaderCardID;
    END

    -- Delete the reader card
    DELETE FROM dbo.ReaderCardID
    WHERE ReaderCardID = @ReaderCardID;

    -- Delete the reader
    DELETE FROM dbo.Reader
    WHERE ReaderCardID = @ReaderCardID;
END;
GO



-- Hàm USP_AddToYourBookshelf để thêm sách vào bookshelf của một độc giả. 1 quyển 1 lần
-- Lấy ra ListBookCode của độc giả đó, thêm BookCode vào ListBookCode.
-- Mỗi 1 độc giả từ đầu đã có bản ghi với ListBookCode là NULL
-- Ví dụ: Nếu ListBookCode = 'A1.NA.2004, A2.LT.2001.C2' và thêm sách có BookCode = 'A1.NA.2004.C3' thì ListBookCode sẽ được cập nhật thành 'A1.NA.2004, A2.LT.2001.C2, A1.NA.2004.C3'
-- Nếu là ListBookCode là NULL thì ListBookCode sẽ được cập nhật thành 'A1.NA.2004.C3'
CREATE PROCEDURE USP_AddToYourBookshelf
    @ReaderCardID NVARCHAR(50),
    @BookCode NVARCHAR(50)
AS
BEGIN
    -- Declare a variable to hold the current ListBookCode
    DECLARE @CurrentListBookCode NVARCHAR(MAX);

    -- Retrieve the current ListBookCode for the given ReaderCardID
    SELECT @CurrentListBookCode = ListBookCode
    FROM YourBookshelf
    WHERE ReaderCardID = @ReaderCardID;

    -- Check if the ListBookCode is NULL
    IF @CurrentListBookCode IS NULL
    BEGIN
        -- Update the record with the new BookCode
        UPDATE YourBookshelf
        SET ListBookCode = @BookCode
        WHERE ReaderCardID = @ReaderCardID;
    END
    ELSE
    BEGIN
        -- Update the existing record by appending the new BookCode
        UPDATE YourBookshelf
        SET ListBookCode = @CurrentListBookCode + ',' + @BookCode
        WHERE ReaderCardID = @ReaderCardID;
    END
END;
GO

-- Hàm USP_RemoveFromYourBookshelf để xóa sách khỏi bookshelf của một độc giả.
-- Lấy ra ListBookCode của độc giả đó, xóa BookCode khỏi ListBookCode.
-- Ví dụ: Nếu ListBookCode = 'A1.NA.2004, A2.LT.2001.C2, A1.NA.2004.C3' và xóa sách có BookCode = 'A1.NA.2004.C3' thì ListBookCode sẽ được cập nhật thành 'A1.NA.2004, A2.LT.2001.C2'
-- Nếu ListBookCode chỉ còn một BookCode thì ListBookCode sẽ được cập nhật thành NULL
CREATE PROCEDURE USP_RemoveFromYourBookshelf
    @ReaderCardID NVARCHAR(50),
    @BookCode NVARCHAR(50)
AS
BEGIN
    -- Declare a variable to hold the current ListBookCode
    DECLARE @CurrentListBookCode NVARCHAR(MAX);

    -- Retrieve the current ListBookCode for the given ReaderCardID
    SELECT @CurrentListBookCode = ListBookCode
    FROM YourBookshelf
    WHERE ReaderCardID = @ReaderCardID;

    -- Check if the ListBookCode contains the BookCode
    IF @CurrentListBookCode IS NOT NULL AND CHARINDEX(@BookCode, @CurrentListBookCode) > 0
    BEGIN
        -- Remove the BookCode from the ListBookCode
        SET @CurrentListBookCode = REPLACE(@CurrentListBookCode, @BookCode, '');

        -- Remove any leading or trailing commas and spaces
        SET @CurrentListBookCode = LTRIM(RTRIM(REPLACE(REPLACE(REPLACE(@CurrentListBookCode, ', ,', ','), ',,', ','), ', ', ',')));

        -- Remove any leading comma
        IF LEFT(@CurrentListBookCode, 1) = ','
        BEGIN
            SET @CurrentListBookCode = SUBSTRING(@CurrentListBookCode, 2, LEN(@CurrentListBookCode) - 1);
        END

        -- Remove any trailing comma
        IF RIGHT(@CurrentListBookCode, 1) = ','
        BEGIN
            SET @CurrentListBookCode = LEFT(@CurrentListBookCode, LEN(@CurrentListBookCode) - 1);
        END

        -- If the resulting ListBookCode is empty, set it to NULL
        IF LEN(@CurrentListBookCode) = 0
        BEGIN
            SET @CurrentListBookCode = NULL;
        END

        -- Update the ListBookCode in the database
        UPDATE YourBookshelf
        SET ListBookCode = @CurrentListBookCode
        WHERE ReaderCardID = @ReaderCardID;
    END
END;
GO


-- Hàm USP_AcceptRequestExtend để chấp nhận yêu cầu gia hạn ReaderCard. 
-- Lấy duration từ card Status của ReaderCardID đó, lấy dateExpired từ ReaderCardID đó, cộng thêm duration (duration ở trong dấu ngoặc sau 'Request Extend') vào dateExpired.
-- duration có thể là: 1 month, 3 months, 6 months, 1 year
-- Cập nhật CardStatus của ReaderCardID đó thành 'Active'.
CREATE PROCEDURE USP_AcceptRequestExtend
    @ReaderCardID NVARCHAR(50)
AS
BEGIN
    -- Declare variables to hold CardStatus and dateExpired
    DECLARE @CardStatus NVARCHAR(100);
    DECLARE @dateExpired DATE;
    DECLARE @duration NVARCHAR(20);

    -- Retrieve the current CardStatus and dateExpired for the given ReaderCardID
    SELECT @CardStatus = CardStatus, @dateExpired = dateExpired
    FROM ReaderCardID
    WHERE ReaderCardID = @ReaderCardID;

    -- Check if the CardStatus contains 'Request Extend'
    IF @CardStatus LIKE 'Request Extend (%'
    BEGIN
        -- Extract the duration from the CardStatus
        SET @duration = SUBSTRING(@CardStatus, CHARINDEX('(', @CardStatus) + 1, CHARINDEX(')', @CardStatus) - CHARINDEX('(', @CardStatus) - 1);

        -- Update the dateExpired based on the duration
        IF @duration = '1 month'
        BEGIN
            SET @dateExpired = DATEADD(MONTH, 1, @dateExpired);
        END
        ELSE IF @duration = '3 months'
        BEGIN
            SET @dateExpired = DATEADD(MONTH, 3, @dateExpired);
        END
        ELSE IF @duration = '6 months'
        BEGIN
            SET @dateExpired = DATEADD(MONTH, 6, @dateExpired);
        END
        ELSE IF @duration = '1 year'
        BEGIN
            SET @dateExpired = DATEADD(YEAR, 1, @dateExpired);
        END

        -- Update the CardStatus to 'Accepted' and the dateExpired in the database
        UPDATE ReaderCardID
        SET CardStatus = 'Active', dateExpired = @dateExpired
        WHERE ReaderCardID = @ReaderCardID;
    END
END;
GO









