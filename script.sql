USE [master]
GO
CREATE DATABASE [Coffee]
GO
USE [Coffee]
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKEKHOANG]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKEKHOANG] (@ngayBD date,@ngayKT date)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where  DateOrder between @ngayBD  and @ngayKT and od.Status=2
--where @ngayBD Between @ngayKT
group by (od.DateOrder)
END
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKENAM]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKENAM] (@Nam int)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		Month(od.DateOrder) as Thang
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where Year([DateOrder])=@Nam and od.Status=2
group by Month(od.DateOrder)

END
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKENGAY]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKENGAY] (@NGay date)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		([TimeOder])
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
		where ([DateOrder])=@NGay and od.Status=2
	GROUP BY ([TimeOder])
END
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKETHANG]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKETHANG] (@thang int,@nam int)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where Month([DateOrder])=@thang and YEAR(DateOrder)=@nam and od.Status=2
group by (od.DateOrder)

END
GO
/****** Object:  StoredProcedure [dbo].[getBillHuyNam]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getBillHuyNam] (@nam int)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and year(DateOrder)=@nam
END



GO
/****** Object:  StoredProcedure [dbo].[getBillHuyNgay]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getBillHuyNgay] (@ngay date)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and (DateOrder)=@ngay
END



GO
/****** Object:  StoredProcedure [dbo].[getBillHuyThang]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROC [dbo].[getBillHuyThang] (@thang int,@nam int)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and Month(DateOrder)=@thang and YEAR(DateOrder)=@nam
END
GO
/****** Object:  StoredProcedure [dbo].[getListBysendMailNgay]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getListBysendMailNgay] (@ngay date)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,o.UsernameEMP,o.DateOrder,o.TimeOder,o.Reason from [Order] o join OrderDetail od on o.IDOrder=od.IDOrder
where o.[Status]=3 and (o.DateOrder) = @ngay
END



GO
/****** Object:  StoredProcedure [dbo].[getListHoaDon]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getListHoaDon]
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status,g.DiscountPromo,c.Discount
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 left join Promotions g on g.NamePromo=o.NamePromo
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo,g.DiscountPromo,c.Discount
END
GO
/****** Object:  StoredProcedure [dbo].[getListHoaDonNgay]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getListHoaDonNgay] (@ngay Date)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 where (o.DateOrder) =@ngay
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo
END

GO
/****** Object:  StoredProcedure [dbo].[getListHoaDonThang]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getListHoaDonThang] (@thang int)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 where MONTH(o.DateOrder) = @thang
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo
END

GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHD]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getTongMvaTongHD] (@ngay date)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where (o.DateOrder) = @ngay) as tongM, 
(Select COUNT(DISTINCT oo.IDOrder) as tongHD FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where (DateOrder) =@ngay) as TongHD
END

GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDKhoang]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getTongMvaTongHDKhoang] (@ngayBD Date,@ngayKT date)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where  DateOrder between @ngayBD  and @ngayKT) as tongM, 
(Select  COUNT(IDOrder) as tongHD FROm [Order]
where  DateOrder between @ngayBD  and @ngayKT) as TongHD
END



GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDNam]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getTongMvaTongHDNam] (@nam int)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where year(o.DateOrder) =@nam) as tongM, 
(Select  COUNT(IDOrder) as tongHD FROm [Order]
where year(DateOrder) =@nam) as TongHD
END




GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDThang]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getTongMvaTongHDThang] (@thang int)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where month(o.DateOrder) =@thang) as tongM, 
(Select  COUNT(IDOrder) as tongHD FROm [Order]
where month(DateOrder) =@thang) as TongHD
END




GO
/****** Object:  StoredProcedure [dbo].[sendmailNam]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[sendmailNam] (@nam int)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,o.UsernameEMP,o.DateOrder,o.TimeOder,o.Reason from [Order] o join OrderDetail od on o.IDOrder=od.IDOrder
where o.[Status]=3 and year(o.DateOrder) =@nam
END



GO
/****** Object:  StoredProcedure [dbo].[sendmailNgay]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[sendmailNgay] (@ngay date)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,o.UsernameEMP,o.DateOrder,o.TimeOder,o.Reason from [Order] o join OrderDetail od on o.IDOrder=od.IDOrder
where o.[Status]=3 and (o.DateOrder) =@ngay
END



GO
/****** Object:  StoredProcedure [dbo].[sendmailThang]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[sendmailThang] (@thang int,@nam int)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,o.UsernameEMP,o.DateOrder,o.TimeOder,o.Reason from [Order] o join OrderDetail od on o.IDOrder=od.IDOrder
where o.[Status]=3 and Month(o.DateOrder) =@thang and Year(o.DateOrder)=@nam
END
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](150) NULL,
	[Email] [nvarchar](150) NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Area]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Area](
	[IDArea] [int] IDENTITY(1,1) NOT NULL,
	[AreaName] [nvarchar](50) NULL,
	[MaxTable] [int] NULL,
 CONSTRAINT [PK_Area] PRIMARY KEY CLUSTERED 
(
	[IDArea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[IDCust] [varchar](10) NOT NULL,
	[CusName] [nvarchar](50) NOT NULL,
	[DateAdd] [date] NOT NULL,
	[DateEnd] [date] NOT NULL,
	[Phone] [varchar](15) NOT NULL,
	[Email] [nvarchar](150) NOT NULL,
	[Discount] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[CCCD] [varchar](50) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[IDCust] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Employee](
	[UsernameEMP] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](150) NULL,
	[NameEMP] [nvarchar](50) NULL,
	[Phone] [varchar](15) NULL,
	[Birthday] [date] NULL,
	[Address] [nvarchar](150) NULL,
	[Sex] [bit] NULL,
	[Email] [varchar](50) NULL,
	[Image] [nvarchar](500) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[UsernameEMP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Order]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Order](
	[IDOrder] [varchar](10) NOT NULL,
	[DateOrder] [date] NULL,
	[TimeOder] [time](7) NULL,
	[UsernameEMP] [nvarchar](50) NULL,
	[IDCust] [varchar](10) NULL,
	[Status] [int] NOT NULL,
	[Reason] [nvarchar](100) NULL,
	[NamePromo] [nvarchar](50) NULL,
	[DiscountOrder] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[IDOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[IDOrder] [varchar](10) NULL,
	[IDProduct] [varchar](10) NULL,
	[IDTable] [varchar](10) NULL,
	[Quantity] [int] NULL,
	[Note] [nvarchar](200) NULL,
	[Reason] [nchar](10) NULL,
	[Status] [bit] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[IDProduct] [varchar](10) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[Price] [float] NULL,
	[Image] [nvarchar](500) NULL,
	[Status] [bit] NULL,
	[IDType] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[IDProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductType]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ProductType](
	[IDType] [int] NOT NULL,
	[TypeName] [nvarchar](50) NULL,
	[Size] [varchar](10) NULL,
 CONSTRAINT [PK_ProductType] PRIMARY KEY CLUSTERED 
(
	[IDType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Promotions]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Promotions](
	[IDPromo] [int] IDENTITY(1,1) NOT NULL,
	[NamePromo] [nvarchar](50) NULL,
	[DiscountPromo] [int] NULL,
	[StartPromo] [date] NULL,
	[EndPromo] [date] NULL,
	[Description] [nvarchar](200) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Promotions] PRIMARY KEY CLUSTERED 
(
	[IDPromo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Table]    Script Date: 12/9/2021 9:54:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Table](
	[IDTable] [varchar](10) NOT NULL,
	[Location] [int] NULL,
	[Status] [int] NULL,
	[IDArea] [nvarchar](50) NULL,
	[TableGroup] [nvarchar](50) NULL,
 CONSTRAINT [PK_Table] PRIMARY KEY CLUSTERED 
(
	[IDTable] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Admin] ([Username], [Password], [Email]) VALUES (N'Admin', N'1', N'manh19qn@gmail.com')
SET IDENTITY_INSERT [dbo].[Area] ON 

INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable]) VALUES (8, N'Lầu 1', NULL)
INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable]) VALUES (9, N'Lầu 2', NULL)
INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable]) VALUES (11, N'Lầu 3', NULL)
SET IDENTITY_INSERT [dbo].[Area] OFF
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH01', N'Mạnh PHẠM', CAST(N'2021-10-10' AS Date), CAST(N'2021-10-11' AS Date), N'0988307480', N'manh@gmail.com', 3, 0, N'9873482342')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH02', N'Mạnh', CAST(N'2021-11-11' AS Date), CAST(N'2021-11-12' AS Date), N'0768373682', N'manh1@gmail.com', 3, 0, N'9873482342')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH03', N'Thành', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 3, 1, N'9873482343')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH04', N'Vương', CAST(N'2021-12-13' AS Date), CAST(N'2022-12-02' AS Date), N'0932472643', N'Thanh@gmail.com', 3, 1, N'9873482344')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH05', N'Thông', CAST(N'2021-12-20' AS Date), CAST(N'2022-12-20' AS Date), N'0936472623', N'Thong@gmail.com', 3, 1, N'9873482345')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH06', N'Tú', CAST(N'2021-01-20' AS Date), CAST(N'2022-02-20' AS Date), N'0936452623', N'TU@gmail.com', 3, 1, N'9873482346')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH07', N'Hạnh', CAST(N'2022-08-30' AS Date), CAST(N'2022-09-30' AS Date), N'0931472623', N'Nga@gmail.com', 3, 1, N'9873482347')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH08', N'Thành hiền', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'NgaThanh@gmail.com', 3, 1, N'9873482348')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH09', N'Tu Thống', CAST(N'2022-04-20' AS Date), CAST(N'2022-05-20' AS Date), N'0931171623', N'Thongtu@gmail.com', 3, 1, N'9873482349')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH10', N'Vương TÚ', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'tunga123@gmail.com', 3, 1, N'9873482310')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH11', N'Tuấn', CAST(N'2021-10-10' AS Date), CAST(N'2021-10-11' AS Date), N'0988307480', N'manh@gmail.com', 3, 0, N'9873482311')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH12', N'Long', CAST(N'2021-11-11' AS Date), CAST(N'2021-11-12' AS Date), N'0768373682', N'manh1@gmail.com', 3, 0, N'9873482312')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH13', N'Lông', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 3, 1, N'9873482313')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH14', N'Lợn', CAST(N'2021-12-13' AS Date), CAST(N'2022-12-02' AS Date), N'0932472643', N'Thanh@gmail.com', 3, 1, N'9873482314')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH15', N'Giang', CAST(N'2021-12-20' AS Date), CAST(N'2022-12-20' AS Date), N'0936472623', N'Thong@gmail.com', 3, 1, N'9873482315')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH16', N'Thanh', CAST(N'2021-01-20' AS Date), CAST(N'2022-02-20' AS Date), N'0936452623', N'TU@gmail.com', 3, 1, N'9873482316')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH17', N'Phú', CAST(N'2022-08-30' AS Date), CAST(N'2022-09-30' AS Date), N'0931472623', N'Nga@gmail.com', 3, 1, N'9873482317')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH18', N'Chúc', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'NgaThanh@gmail.com', 3, 1, N'9873482318')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH19', N'Linh', CAST(N'2022-04-20' AS Date), CAST(N'2022-05-20' AS Date), N'0931171623', N'Thongtu@gmail.com', 3, 1, N'9873482319')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH20', N'TÚ Lan', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'tunga123@gmail.com', 3, 1, N'9873482320')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH21', N'Lươn', CAST(N'2021-10-10' AS Date), CAST(N'2021-10-11' AS Date), N'0988307480', N'manh@gmail.com', 3, 0, N'98734823421')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH22', N'Trang', CAST(N'2021-11-11' AS Date), CAST(N'2021-11-12' AS Date), N'0768373682', N'manh1@gmail.com', 3, 0, N'9873482322')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH23', N'Hiệp', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 3, 1, N'9873482323')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH24', N'Thảo', CAST(N'2021-12-13' AS Date), CAST(N'2022-12-02' AS Date), N'0932472643', N'Thanh@gmail.com', 3, 1, N'9873482327')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH25', N'Hiển', CAST(N'2021-12-20' AS Date), CAST(N'2022-12-20' AS Date), N'0936472623', N'Thong@gmail.com', 3, 1, N'9873482328')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH26', N'Nam', CAST(N'2021-01-20' AS Date), CAST(N'2022-02-20' AS Date), N'0936452623', N'TU@gmail.com', 3, 1, N'9873482329')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH27', N'Nữ', CAST(N'2022-08-30' AS Date), CAST(N'2022-09-30' AS Date), N'0931472623', N'Nga@gmail.com', 3, 1, N'9873482330')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH28', N'Trinh', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'NgaThanh@gmail.com', 3, 1, N'9873482331')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH29', N'Sơn', CAST(N'2022-04-20' AS Date), CAST(N'2022-05-20' AS Date), N'0931171623', N'Thongtu@gmail.com', 3, 1, N'9873482332')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH30', N'Khánh', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'tunga123@gmail.com', 3, 1, N'9873482333')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH34', N'Hiệp', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 7, 1, N'9873482325')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH43', N'Hiệp', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 3, 1, N'9873482326')
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status], [CCCD]) VALUES (N'KH44', N'1212', CAST(N'2021-12-07' AS Date), CAST(N'2022-01-06' AS Date), N'0988534539', N'as@gmail.com', 0, 1, NULL)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP01', N'Cafe Sữa', 25000, N'cafesua.jpg', 0, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP02', N'Cafe Đen', 25000, N'cafeden.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP03', N'Trà ĐÀo', 35000, N'daossua.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP04', N'Trà Chanh', 25000, N'traChanh.jpg', 0, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP05', N'Trà gừng', 25000, N'tac-dung-cua-tra-gung-5.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP06', N'Trà đào cam xả', 35000, N'Chalinee-Udomchanachai-3.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP07', N'Bạc xỉu đá', 25000, N'BacXiuDa.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP08', N'Bạc xỉu nóng', 25000, N'BacXiuNong.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP09', N'Cafe Kem', 30000, N'cach-pha-cafe-bac-xiu.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP10', N'Cafe Cốt Dừa', 30000, N'cafeCotDua.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP11', N'Cafe Đá Xay', 25000, N'Ca-phe-sua-da.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP12', N'Trà sữa Dâu Tây', 35000, N'traSuaDauTay.jpg', 0, 4)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP13', N'Trà sữa Dâu Tây', 35000, N'traSuaDauTay.jpg', 1, 3)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP14', N'Trang', 2000000, N'gai-xinh-1.jpg', 1, 6)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP15', N'Đào', 1000000, N'gai-xinh-mac-vay-ngan-15-750x937.jpg', 1, 7)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP16', N'Quỳnh', 500000, N'anh-girl-xinh-han-quoc-mac-vay-ngan.jpg', 1, 7)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP17', N'Trà sữa Thông Pro', 1000000, N'sp17.jpg', 1, 8)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP18', N'Sừa trà Pro Thông', 100000, N'sp18.jpg', 1, 9)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP19', N'Cafe Sữa Pro', 25000, N'sp19.jpg', 0, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP20', N'Cafe Đen Pro', 25000, N'sp20.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP21', N'Trà ĐÀo Pro', 35000, N'sp21.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP22', N'Trà Chanh Pro', 25000, N'sp22.jpg', 0, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP23', N'Trà gừng Pro', 25000, N'sp23.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP24', N'Trà đào cam xả Pro', 35000, N'sp24.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP25', N'Bạc xỉu đá Pro', 25000, N'sp25.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP26', N'Bạc xỉu nóng pro', 25000, N'sp26.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP27', N'Cafe Kem Pro', 30000, N'sp27.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP28', N'Cafe Cốt Dừa Thông Pro', 30000, N'sp28.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP29', N'Cafe Đá Xay Pro', 25000, N'sp29.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP30', N'Trà sữa Dâu Tây Pro', 35000, N'sp30.jpg', 0, 4)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP31', N'ghjghj', 0, N'sp4.jpg', 1, 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (1, N'Cafe', NULL)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (2, N'Trà', NULL)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (3, N'Trà sữa', N'L')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (4, N'Trà sữa', N'M')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (5, N'Nước ép', NULL)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (6, N'Cafe ôm', N'chân dài')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (7, N'Cafe ôm', N'chân ngon')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (8, N'Bạch tuộc', N'')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (9, N'Cá sấu ', N'')
SET IDENTITY_INSERT [dbo].[Promotions] ON 

INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (1, N'Khai Trương', 50, CAST(N'2020-02-02' AS Date), CAST(N'2021-01-01' AS Date), N'Khai trương Giảm Giá', 0)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (2, N'Tết', 15, CAST(N'2020-03-01' AS Date), CAST(N'2021-03-01' AS Date), N'Sự Kiện Tết', 1)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (3, N'Voucher Thongpro', 90, CAST(N'2020-03-01' AS Date), CAST(N'2021-03-01' AS Date), N'follow thongpro để nhận mã giảm giá :D', 1)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (5, N'Thông Prof', 5, CAST(N'2021-11-22' AS Date), CAST(N'2021-12-07' AS Date), N'Ae nhà pro', 1)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (6, N'Mạnhpro', 50, CAST(N'2021-12-06' AS Date), CAST(N'2021-12-21' AS Date), N'', 1)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (7, N'Test', 50, CAST(N'2021-12-09' AS Date), CAST(N'2021-12-24' AS Date), N'', 1)
SET IDENTITY_INSERT [dbo].[Promotions] OFF
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB001', 1, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB002', 3, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB003', 3, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB004', 4, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB006', 6, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB007', 7, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB008', 8, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB009', 8, 0, N'8', NULL)
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([IDCust])
REFERENCES [dbo].[Customer] ([IDCust])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Employee] FOREIGN KEY([UsernameEMP])
REFERENCES [dbo].[Employee] ([UsernameEMP])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Employee]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([IDOrder])
REFERENCES [dbo].[Order] ([IDOrder])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([IDProduct])
REFERENCES [dbo].[Product] ([IDProduct])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Table] FOREIGN KEY([IDTable])
REFERENCES [dbo].[Table] ([IDTable])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Table]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_ProductType] FOREIGN KEY([IDType])
REFERENCES [dbo].[ProductType] ([IDType])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_ProductType]
GO
