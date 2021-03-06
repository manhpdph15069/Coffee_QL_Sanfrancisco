USE [master]
GO
CREATE DATABASE [Coffee]
GO
USE [Coffee]
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKE]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKE]
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price])-(Sum(hv.[Quantity]*[Price])*od.DiscountOrder)/100 as Tien,
		od.DateOrder
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
		where od.Status=2
	GROUP BY od.DateOrder,od.DiscountOrder
END

GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKEKHOANG]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKEKHOANG] (@ngayBD date,@ngayKT date)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price])-(Sum(hv.[Quantity]*[Price])*od.DiscountOrder)/100 as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where  DateOrder between @ngayBD  and @ngayKT and od.Status=2
--where @ngayBD Between @ngayKT
group by (od.DateOrder),od.DiscountOrder
END

GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKENAM]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[DT_THONGKENGAY]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKENGAY] (@NGay date)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price])-(Sum(hv.[Quantity]*[Price])*od.DiscountOrder)/100 as Tien,
		([TimeOder])
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
		where ([DateOrder])=@NGay and od.Status=2
	GROUP BY ([TimeOder]),od.DiscountOrder
END

GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKETHANG]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKETHANG] (@thang int,@nam int)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price])-(Sum(hv.[Quantity]*[Price])*od.DiscountOrder)/100 as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where Month([DateOrder])=@thang and YEAR(DateOrder)=@nam and od.Status=2
group by (od.DateOrder),od.DiscountOrder

END

GO
/****** Object:  StoredProcedure [dbo].[getBillHuyNam]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getBillHuyNam] (@nam int)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and year(DateOrder)=@nam
END




GO
/****** Object:  StoredProcedure [dbo].[getBillHuyNgay]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getBillHuyNgay] (@ngay date)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and (DateOrder)=@ngay
END




GO
/****** Object:  StoredProcedure [dbo].[getBillHuyThang]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROC [dbo].[getBillHuyThang] (@thang int,@nam int)
AS BEGIN
select count(IDOrder) as tongBillHuy from [Order] where [Status]=3 and Month(DateOrder)=@thang and YEAR(DateOrder)=@nam
END

GO
/****** Object:  StoredProcedure [dbo].[getListBysendMailNgay]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[getListHoaDon]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getListHoaDon]
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status,o.DiscountOrder
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 left join Promotions g on g.NamePromo=o.NamePromo
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo,o.DiscountOrder
END

GO
/****** Object:  StoredProcedure [dbo].[getListHoaDonNAM]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROC [dbo].[getListHoaDonNAM] (@nam int)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status,o.DiscountOrder
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 where Year(o.DateOrder) = @nam
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo,o.DiscountOrder
END
GO
/****** Object:  StoredProcedure [dbo].[getListHoaDonNgay]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getListHoaDonNgay] (@ngay Date)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status,o.DiscountOrder
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 where (o.DateOrder) =@ngay
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo,o.DiscountOrder
END


GO
/****** Object:  StoredProcedure [dbo].[getListHoaDonThang]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getListHoaDonThang] (@thang int,@nam int)
AS BEGIN
select DISTINCT (o.IDOrder) as IDHD,e.NameEMP,c.CusName,o.NamePromo,o.DateOrder,o.TimeOder,o.Reason,Sum(od.Quantity*p.Price) as TongTien,o.Status,o.DiscountOrder
 from [Order] o 
 join OrderDetail od on o.IDOrder=od.IDOrder
 join Product p on od.IDProduct=p.IDProduct
 left join Employee e on e.UsernameEMP=o.UsernameEMP
 left join Customer c on c.IDCust=o.IDCust
 where MONTH(o.DateOrder) = @thang and Year(o.DateOrder) = @nam
 group by (o.IDOrder),e.NameEMP,o.DateOrder,o.TimeOder,o.Reason,o.Status,c.CusName,o.NamePromo,o.DiscountOrder
END

GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHD]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDKhoang]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDNam]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getTongMvaTongHDNam] (@nam int)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where year(o.DateOrder) =@nam) as tongM, 
(Select COUNT(IDOrder) as tongHD FROm [Order] o
where year(DateOrder) =@nam and o.IDOrder  in(select IDOrder from OrderDetail)) as TongHD
END


GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDTC]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getTongMvaTongHDTC] 
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
) as tongM, 
(Select COUNT(DISTINCT oo.IDOrder) as tongHD FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
) as TongHD
END

GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDThang]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sendmailNam]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sendmailNgay]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sendmailThang]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  Table [dbo].[Admin]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  Table [dbo].[Area]    Script Date: 12/10/2021 10:52:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Area](
	[IDArea] [int] IDENTITY(1,1) NOT NULL,
	[AreaName] [nvarchar](50) NULL,
	[MaxTable] [int] NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK_Area] PRIMARY KEY CLUSTERED 
(
	[IDArea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 12/10/2021 10:52:00 PM ******/
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
	[Status] [int] NOT NULL,
	[CCCD] [varchar](50) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[IDCust] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  Table [dbo].[Order]    Script Date: 12/10/2021 10:52:00 PM ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 12/10/2021 10:52:00 PM ******/
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
	[Reason] [nvarchar](550) NULL,
	[Status] [bit] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/10/2021 10:52:00 PM ******/
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
	[Status] [int] NULL,
	[IDType] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[IDProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductType]    Script Date: 12/10/2021 10:52:00 PM ******/
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
	[Status] [bit] NULL,
 CONSTRAINT [PK_ProductType] PRIMARY KEY CLUSTERED 
(
	[IDType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Promotions]    Script Date: 12/10/2021 10:52:00 PM ******/
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
	[Status] [int] NULL,
 CONSTRAINT [PK_Promotions] PRIMARY KEY CLUSTERED 
(
	[IDPromo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Table]    Script Date: 12/10/2021 10:52:00 PM ******/
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
INSERT [dbo].[Admin] ([Username], [Password], [Email]) VALUES (N'Admin', N'1', N'thanhtvph15016@fpt.edu.vn')
SET IDENTITY_INSERT [dbo].[Area] ON 

INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable], [Status]) VALUES (8, N'Lầu 1', NULL, 1)
INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable], [Status]) VALUES (9, N'Lầu 2', NULL, 0)
INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable], [Status]) VALUES (11, N'Lầu 3', NULL, 0)
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
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'MANHNV', N'111', N'Test', N'0988307800', CAST(N'2021-11-17' AS Date), N'QN', 1, N'manh19qn@gmail.com', NULL, 0)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'ManhP', N'111', N'Mạnh', N'0988307480', CAST(N'2021-12-10' AS Date), N'Quảng Ninh', 1, N'manh@gmail.com', NULL, 0)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'Thằn', N'1', N'Thằn', N'0978346813', CAST(N'2002-01-22' AS Date), N'NGhệ An', 1, N'Thamh@gmail.com', N'67407534_465206764333003_5213630654238949376_n.jpg', 0)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'THANHNV', N'1', N'Thằn', N'0978346813', CAST(N'2002-01-22' AS Date), N'NGhệ An', 1, N'Thamh@gmail.com', N'67407534_465206764333003_5213630654238949376_n.jpg', 0)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'Tú', N'tudu', N'TÚn', N'0987876700', CAST(N'2021-11-16' AS Date), N'Hà Nam', 1, N'Tu@gmail.com', NULL, 0)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'VUONGNV', N'1', N'Vươn', N'098257682', CAST(N'2002-11-13' AS Date), N'Phú Thọ', 0, N'vuuong@gmail.co', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD001', CAST(N'2021-12-02' AS Date), CAST(N'22:56:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD002', CAST(N'2021-12-02' AS Date), CAST(N'22:58:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 12:00 | 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD003', CAST(N'2021-12-02' AS Date), CAST(N'23:04:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 11:58 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD004', CAST(N'2021-12-02' AS Date), CAST(N'23:06:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', N'Mạnhpro', 50)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD005', CAST(N'2021-12-03' AS Date), CAST(N'11:42:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD006', CAST(N'2021-12-03' AS Date), CAST(N'11:43:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 12:01 | 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD007', CAST(N'2021-12-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD008', CAST(N'2021-12-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD009', CAST(N'2021-12-03' AS Date), CAST(N'17:25:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD010', CAST(N'2021-12-03' AS Date), CAST(N'22:42:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD011', CAST(N'2021-12-03' AS Date), CAST(N'22:43:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD012', CAST(N'2021-12-03' AS Date), CAST(N'22:46:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD013', CAST(N'2021-12-04' AS Date), CAST(N'22:07:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD014', CAST(N'2021-12-04' AS Date), CAST(N'23:44:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD015', CAST(N'2021-12-04' AS Date), CAST(N'23:51:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD016', CAST(N'2021-12-05' AS Date), CAST(N'09:47:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD017', CAST(N'2021-12-05' AS Date), CAST(N'13:03:00' AS Time), N'MANHNV', NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD018', CAST(N'2021-12-05' AS Date), CAST(N'13:03:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD019', CAST(N'2021-08-02' AS Date), CAST(N'22:56:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD020', CAST(N'2021-07-02' AS Date), CAST(N'22:58:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 12:00 | 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD021', CAST(N'2021-07-02' AS Date), CAST(N'23:04:00' AS Time), NULL, N'KH04', 2, N'Admin khôi phục lại lúc 11:58 03-12-2021', NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD022', CAST(N'2021-07-02' AS Date), CAST(N'23:06:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', N'Khai Trương', 50)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD023', CAST(N'2021-07-03' AS Date), CAST(N'11:42:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD024', CAST(N'2021-07-03' AS Date), CAST(N'11:43:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 12:01 | 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD025', CAST(N'2021-07-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD026', CAST(N'2021-06-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD027', CAST(N'2021-06-03' AS Date), CAST(N'17:25:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD028', CAST(N'2021-06-03' AS Date), CAST(N'22:42:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD029', CAST(N'2021-06-03' AS Date), CAST(N'22:43:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD030', CAST(N'2021-05-03' AS Date), CAST(N'22:46:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD031', CAST(N'2021-05-04' AS Date), CAST(N'22:07:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD032', CAST(N'2021-05-04' AS Date), CAST(N'23:44:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD033', CAST(N'2021-05-04' AS Date), CAST(N'23:51:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD034', CAST(N'2021-04-05' AS Date), CAST(N'09:47:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD035', CAST(N'2021-04-05' AS Date), CAST(N'13:03:00' AS Time), N'MANHNV', NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD036', CAST(N'2021-04-05' AS Date), CAST(N'13:03:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD037', CAST(N'2021-03-03' AS Date), CAST(N'11:43:00' AS Time), NULL, NULL, 2, N'Admin khôi phục lại lúc 12:01 | 03-12-2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD038', CAST(N'2021-03-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD039', CAST(N'2021-03-03' AS Date), CAST(N'17:24:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD040', CAST(N'2021-03-03' AS Date), CAST(N'17:25:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD041', CAST(N'2021-02-03' AS Date), CAST(N'22:42:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD042', CAST(N'2021-02-03' AS Date), CAST(N'22:43:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD043', CAST(N'2021-02-03' AS Date), CAST(N'22:46:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD044', CAST(N'2021-02-04' AS Date), CAST(N'22:07:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD045', CAST(N'2021-02-04' AS Date), CAST(N'23:44:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD046', CAST(N'2021-01-04' AS Date), CAST(N'23:51:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD047', CAST(N'2021-01-05' AS Date), CAST(N'09:47:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD048', CAST(N'2021-01-05' AS Date), CAST(N'13:03:00' AS Time), N'MANHNV', NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD049', CAST(N'2021-01-05' AS Date), CAST(N'13:03:00' AS Time), NULL, N'KH01', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD050', CAST(N'2021-08-03' AS Date), CAST(N'22:42:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD051', CAST(N'2021-08-03' AS Date), CAST(N'22:43:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD052', CAST(N'2021-08-03' AS Date), CAST(N'22:46:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD053', CAST(N'2021-09-04' AS Date), CAST(N'22:07:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD054', CAST(N'2021-09-04' AS Date), CAST(N'23:44:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD055', CAST(N'2021-09-04' AS Date), CAST(N'23:51:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD056', CAST(N'2021-11-05' AS Date), CAST(N'09:47:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD057', CAST(N'2021-11-05' AS Date), CAST(N'13:03:00' AS Time), N'MANHNV', NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD058', CAST(N'2021-11-05' AS Date), CAST(N'13:03:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD059', CAST(N'2021-12-06' AS Date), CAST(N'12:10:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD060', CAST(N'2021-12-06' AS Date), CAST(N'13:04:00' AS Time), N'MANHNV', NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD061', CAST(N'2021-12-07' AS Date), CAST(N'20:32:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD062', CAST(N'2021-12-07' AS Date), CAST(N'20:33:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD063', CAST(N'2021-12-07' AS Date), CAST(N'23:55:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD064', CAST(N'2021-12-07' AS Date), CAST(N'23:56:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD065', CAST(N'2021-12-07' AS Date), CAST(N'23:56:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD066', CAST(N'2021-12-07' AS Date), CAST(N'23:57:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD067', CAST(N'2021-12-07' AS Date), CAST(N'23:58:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD068', CAST(N'2021-12-07' AS Date), CAST(N'23:58:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD069', CAST(N'2021-12-07' AS Date), CAST(N'23:59:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD070', CAST(N'2021-12-07' AS Date), CAST(N'23:59:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD071', CAST(N'2021-12-08' AS Date), CAST(N'00:00:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD072', CAST(N'2021-12-08' AS Date), CAST(N'00:01:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD073', CAST(N'2021-12-08' AS Date), CAST(N'00:01:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD074', CAST(N'2021-12-08' AS Date), CAST(N'00:01:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD075', CAST(N'2021-12-08' AS Date), CAST(N'00:02:00' AS Time), NULL, NULL, 1, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD076', CAST(N'2021-12-08' AS Date), CAST(N'00:02:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD077', CAST(N'2021-12-08' AS Date), CAST(N'00:02:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD078', CAST(N'2021-12-08' AS Date), CAST(N'23:27:00' AS Time), NULL, N'KH01', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD079', CAST(N'2021-12-08' AS Date), CAST(N'23:29:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD080', CAST(N'2021-12-08' AS Date), CAST(N'23:29:00' AS Time), NULL, N'KH01', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD081', CAST(N'2021-12-08' AS Date), CAST(N'23:41:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD082', CAST(N'2021-12-08' AS Date), CAST(N'23:43:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD083', CAST(N'2021-12-09' AS Date), CAST(N'00:29:00' AS Time), NULL, N'KH01', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD084', CAST(N'2021-12-09' AS Date), CAST(N'00:30:00' AS Time), NULL, N'KH02', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD085', CAST(N'2021-12-09' AS Date), CAST(N'01:04:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD086', CAST(N'2021-12-09' AS Date), CAST(N'01:05:00' AS Time), NULL, NULL, 2, NULL, N'Test', 50)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD087', CAST(N'2021-12-09' AS Date), CAST(N'12:22:00' AS Time), NULL, N'KH01', 2, NULL, NULL, 3)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD088', CAST(N'2021-12-09' AS Date), CAST(N'12:23:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD089', CAST(N'2021-12-09' AS Date), CAST(N'19:01:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD090', CAST(N'2021-12-09' AS Date), CAST(N'19:02:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD091', CAST(N'2021-10-09' AS Date), CAST(N'19:02:00' AS Time), NULL, NULL, 2, NULL, NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD092', CAST(N'2021-12-10' AS Date), CAST(N'14:03:00' AS Time), NULL, NULL, 2, N'Admin thanh toán lúc : 02:03 PM | 10/12/2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD093', CAST(N'2021-12-10' AS Date), CAST(N'14:04:00' AS Time), NULL, NULL, 2, N'Admin thanh toán lúc : 02:04 PM | 10/12/2021', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD094', CAST(N'2020-12-10' AS Date), CAST(N'14:04:00' AS Time), NULL, NULL, 2, N'Admin thanh toán lúc : 02:04 PM | 10/12/2021', NULL, 50)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD095', CAST(N'2021-12-10' AS Date), CAST(N'14:16:00' AS Time), NULL, NULL, 3, N'Nhân viên order nh?m', NULL, 0)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason], [NamePromo], [DiscountOrder]) VALUES (N'OD096', CAST(N'2021-12-10' AS Date), CAST(N'14:16:00' AS Time), NULL, NULL, 2, N'Admin thanh toán lúc : 02:17 PM | 10/12/2021', NULL, 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP01', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP01', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP18', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP03', N'TB006', 1, N'', N'thích     ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD010', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD011', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD012', N'SP02', N'TB010', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP02', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP01', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP01', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP18', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP03', N'TB006', 1, N'', N'thích     ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD010', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD011', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD012', N'SP02', N'TB010', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP02', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP03', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD008', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD019', N'SP01', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD019', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD020', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD021', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD020', N'SP01', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD022', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD023', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD024', N'SP18', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD025', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD026', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD027', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD028', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD029', N'SP03', N'TB006', 1, N'', N'thích     ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD030', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD031', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD032', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD033', N'SP02', N'TB010', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD034', N'SP02', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD035', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD036', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD037', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD038', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD039', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD040', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD041', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD042', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD043', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD044', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD045', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD046', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD047', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD048', N'SP02', N'TB001', 1, N'', N'          ', 0)
GO
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD049', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD032', N'SP03', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD034', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP01', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP01', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP18', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD006', N'SP03', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP02', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP03', N'TB006', 1, N'', N'thích     ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD009', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD010', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD011', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD012', N'SP02', N'TB010', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP02', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD013', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP02', N'TB009', 1, N'', N'h         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD014', N'SP03', N'TB009', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD015', N'SP02', N'TB009', 1, N'', N'1         ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD016', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD017', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP03', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD008', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD007', N'SP03', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD008', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD050', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD051', N'SP02', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD052', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD053', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD054', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD055', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD056', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD057', N'SP03', N'TB002', 1, N'', N'          ', 1)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD058', N'SP02', N'TB006', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD059', N'SP01', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD059', N'SP01', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD060', N'SP01', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD060', N'SP02', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD060', N'SP03', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD061', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD061', N'SP02', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD062', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD062', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD065', N'SP05', N'TB003', 1, N'Tách từ hóa đơn :OD064', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD068', N'SP03', N'TB003', 1, N'Tách từ hóa đơn :OD066', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD066', N'SP05', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD073', N'SP05', N'TB010', 2, N'Tách : 2', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD078', N'SP03', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD078', N'SP05', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD079', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD079', N'SP03', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD081', N'SP03', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD081', N'SP03', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD081', N'SP03', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD082', N'SP03', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD082', N'SP05', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD084', N'SP03', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD084', N'SP05', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD086', N'SP03', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD086', N'SP05', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD087', N'SP03', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD087', N'SP05', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD089', N'SP03', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD091', N'SP03', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD070', N'SP05', N'TB003', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD071', N'SP03', N'TB003', 1, N'Tách từ hóa đơn :OD070', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD076', N'SP05', N'TB010', 1, N'Tách từ hóa đơn :OD073', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD077', N'SP02', N'TB010', 1, N'Tách từ hóa đơn :OD073', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD088', N'SP03', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD076', N'SP05', N'TB010', 1, N'Tách từ hóa đơn :OD073Tách : 1', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD080', N'SP03', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD080', N'SP05', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD080', N'SP03', N'TB004', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD083', N'SP03', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD083', N'SP05', N'TB001', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD085', N'SP03', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD085', N'SP05', N'TB002', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD090', N'SP03', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD092', N'SP02', N'TB012', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD093', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD094', N'SP03', N'TB008', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD096', N'SP02', N'TB012', 2, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD096', N'SP03', N'TB012', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD095', N'SP02', N'TB012', 1, N'', N'          ', 0)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP01', N'Cafe Sữa', 25000, N'cafesua.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP02', N'Cafe Đen', 25000, N'cafeden.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP03', N'Trà ĐÀo', 35000, N'daossua.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP04', N'Trà Chanh', 25000, N'traChanh.jpg', 0, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP05', N'Trà gừng', 25000, N'tac-dung-cua-tra-gung-5.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP06', N'Trà đào cam xả', 35000, N'sp18.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP07', N'Bạc xỉu đá', 25000, N'BacXiuDa.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP08', N'Bạc xỉu nóng', 25000, N'BacXiuNong.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP09', N'Cafe Kem', 30000, N'cach-pha-cafe-bac-xiu.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP10', N'Cafe Cốt Dừa', 30000, N'cafeCotDua.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP11', N'Cafe Đá Xay', 25000, N'Ca-phe-sua-da.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP12', N'Trà sữa Dâu Tây', 35000, N'traSuaDauTay.jpg', 0, 4)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP13', N'Trà sữa Dâu Tây', 35000, N'traSuaDauTay.jpg', 1, 3)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP14', N'Trang', 2000000, N'coffeeDaSay.jpg', 1, 6)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP15', N'Đào', 1000000, N'sp8.JPG', 1, 7)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP16', N'Quỳnh', 500000, N'sp14.jpg', 1, 7)
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
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP31', N'ghjghj', 0, N'sp4.jpg', 2, 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (1, N'Cafe', NULL, 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (2, N'Trà', NULL, 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (3, N'Trà sữa', N'L', 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (4, N'Trà sữa', N'M', 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (5, N'Nước ép', NULL, 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (6, N'Cafe Fe', N'LY', 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (7, N'Cafe Pro', N'Ca', 1)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (8, N'Bạch tuộc', N'', 0)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size], [Status]) VALUES (9, N'Cá sấu ', N'', 0)
SET IDENTITY_INSERT [dbo].[Promotions] ON 

INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (1, N'Khai Trương', 50, CAST(N'2020-02-02' AS Date), CAST(N'2021-01-01' AS Date), N'Khai trương Giảm Giá', 0)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (2, N'Tết', 15, CAST(N'2020-03-01' AS Date), CAST(N'2021-03-01' AS Date), N'Sự Kiện Tết', 0)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (3, N'Voucher Thongpro', 90, CAST(N'2020-03-01' AS Date), CAST(N'2021-03-01' AS Date), N'follow thongpro để nhận mã giảm giá :D', 0)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (5, N'Thông Prof', 5, CAST(N'2021-11-22' AS Date), CAST(N'2021-12-07' AS Date), N'Ae nhà pro', 0)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (6, N'Mạnhpro', 50, CAST(N'2021-12-06' AS Date), CAST(N'2021-12-21' AS Date), N'', 1)
INSERT [dbo].[Promotions] ([IDPromo], [NamePromo], [DiscountPromo], [StartPromo], [EndPromo], [Description], [Status]) VALUES (7, N'Test', 50, CAST(N'2021-12-09' AS Date), CAST(N'2021-12-24' AS Date), N'', 1)
SET IDENTITY_INSERT [dbo].[Promotions] OFF
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB001', 1, 0, N'9', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB002', 3, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB003', 3, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB004', 4, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB006', 6, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB007', 7, 1, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB008', 8, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB009', 9, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB010', 10, 0, N'9', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB011', 11, 0, N'13', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB012', 1, 0, N'8', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea], [TableGroup]) VALUES (N'TB013', 1, 0, N'11', NULL)
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
