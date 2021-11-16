USE [Coffee]
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKEKHOANG]    Script Date: 11/16/2021 12:47:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[DT_THONGKEKHOANG] (@ngayBD date,@ngayKT date)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where  DateOrder between @ngayBD  and @ngayKT
--where @ngayBD Between @ngayKT
group by (od.DateOrder)
END



GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKENAM]    Script Date: 11/16/2021 12:47:59 AM ******/
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
where Year([DateOrder])=@Nam
group by Month(od.DateOrder)

END



GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKENGAY]    Script Date: 11/16/2021 12:47:59 AM ******/
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
		where ([DateOrder])=@NGay
	GROUP BY ([TimeOder])
END
GO
/****** Object:  StoredProcedure [dbo].[DT_THONGKETHANG]    Script Date: 11/16/2021 12:47:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DT_THONGKETHANG] (@thang int)
AS BEGIN
	SELECT
		Sum(hv.[Quantity]*[Price]) as Tien,
		(od.DateOrder) as Ngay
	FROM [Order] od
		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder
		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct
where Month([DateOrder])=@thang
group by (od.DateOrder)

END



GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHD]    Script Date: 11/16/2021 12:47:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[getTongMvaTongHD] (@ngay date)
AS BEGIN
Select  (Select  Sum(oo.Quantity) FROm OrderDetail oo join dbo.[Order] o on oo.IDOrder = o.IDOrder
where (o.DateOrder) =@ngay) as tongM, 
(Select  COUNT(IDOrder) as tongHD FROm [Order]
where (DateOrder) =@ngay) as TongHD
END
GO
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDNam]    Script Date: 11/16/2021 12:47:59 AM ******/
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
/****** Object:  StoredProcedure [dbo].[getTongMvaTongHDThang]    Script Date: 11/16/2021 12:47:59 AM ******/
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
/****** Object:  Table [dbo].[Admin]    Script Date: 11/16/2021 12:47:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](150) NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Area]    Script Date: 11/16/2021 12:48:00 AM ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 11/16/2021 12:48:00 AM ******/
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
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[IDCust] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 11/16/2021 12:48:00 AM ******/
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
	[Image] [nvarchar](50) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[UsernameEMP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Order]    Script Date: 11/16/2021 12:48:00 AM ******/
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
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[IDOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/16/2021 12:48:00 AM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 11/16/2021 12:48:00 AM ******/
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
	[Image] [nvarchar](50) NULL,
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
/****** Object:  Table [dbo].[ProductType]    Script Date: 11/16/2021 12:48:00 AM ******/
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
/****** Object:  Table [dbo].[Table]    Script Date: 11/16/2021 12:48:00 AM ******/
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
	[IDArea] [int] NULL,
 CONSTRAINT [PK_Table] PRIMARY KEY CLUSTERED 
(
	[IDTable] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Admin] ([Username], [Password]) VALUES (N'Admin', N'1')
SET IDENTITY_INSERT [dbo].[Area] ON 

INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable]) VALUES (1, N'1', 20)
INSERT [dbo].[Area] ([IDArea], [AreaName], [MaxTable]) VALUES (2, N'2', 20)
SET IDENTITY_INSERT [dbo].[Area] OFF
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH01', N'Mạnh PHẠM', CAST(N'2021-10-10' AS Date), CAST(N'2021-10-11' AS Date), N'0988307480', N'manh@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH02', N'Mạnh', CAST(N'2021-11-11' AS Date), CAST(N'2021-11-12' AS Date), N'0768373682', N'manh1@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH03', N'Thành', CAST(N'2021-12-12' AS Date), CAST(N'2022-12-01' AS Date), N'0932472623', N'Thanh@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH04', N'Vương', CAST(N'2021-12-13' AS Date), CAST(N'2022-12-02' AS Date), N'0932472643', N'Thanh@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH05', N'Thông', CAST(N'2021-12-20' AS Date), CAST(N'2022-12-20' AS Date), N'0936472623', N'Thong@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH06', N'Tú', CAST(N'2021-01-20' AS Date), CAST(N'2022-02-20' AS Date), N'0936452623', N'TU@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH07', N'Nga', CAST(N'2022-08-30' AS Date), CAST(N'2022-09-30' AS Date), N'0931472623', N'Nga@gmail.com', 3, 0)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH08', N'Thành Nga', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'NgaThanh@gmail.com', 3, 0)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH09', N'Tu Thống', CAST(N'2022-04-20' AS Date), CAST(N'2022-05-20' AS Date), N'0931171623', N'Thongtu@gmail.com', 3, 1)
INSERT [dbo].[Customer] ([IDCust], [CusName], [DateAdd], [DateEnd], [Phone], [Email], [Discount], [Status]) VALUES (N'KH10', N'Vương TÚ Nga', CAST(N'2022-03-20' AS Date), CAST(N'2022-04-20' AS Date), N'0936471623', N'tunga123@gmail.com', 3, 1)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'MANHNV', N'1', N'Phạm MẠnh', N'0988300492', CAST(N'2002-10-01' AS Date), N'Quảng Ninh', 1, N'manhh@gmail.com', N'manhdepzai.jpg', 1)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'THANHNV', N'1', N'Thằn', N'0978346813', CAST(N'2002-01-10' AS Date), N'NGhệ An', 1, N'Thamh@gmail.com', N'thanhgiaosu.jpg', 1)
INSERT [dbo].[Employee] ([UsernameEMP], [Password], [NameEMP], [Phone], [Birthday], [Address], [Sex], [Email], [Image], [Status]) VALUES (N'VUONGNV', N'1', N'Vương', N'0982576823', CAST(N'2002-11-11' AS Date), N'Phú Thọ', 1, N'vuuong@gmail.com', N'vuong.img', 1)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason]) VALUES (N'OD001', CAST(N'2021-04-11' AS Date), CAST(N'16:00:00' AS Time), N'MANHNV', N'KH01', 1, NULL)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason]) VALUES (N'OD002', CAST(N'2021-11-15' AS Date), CAST(N'22:30:00' AS Time), N'THANHNV', NULL, 2, NULL)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason]) VALUES (N'OD003', CAST(N'2021-11-15' AS Date), CAST(N'22:40:00' AS Time), N'THANHNV', NULL, 2, NULL)
INSERT [dbo].[Order] ([IDOrder], [DateOrder], [TimeOder], [UsernameEMP], [IDCust], [Status], [Reason]) VALUES (N'OD004', CAST(N'2021-11-15' AS Date), CAST(N'23:33:00' AS Time), N'THANHNV', NULL, 2, NULL)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD001', N'SP01', N'TB001', 2, NULL, NULL, NULL)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP01', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD002', N'SP01', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD003', N'SP01', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP01', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[OrderDetail] ([IDOrder], [IDProduct], [IDTable], [Quantity], [Note], [Reason], [Status]) VALUES (N'OD004', N'SP01', N'TB007', 1, N'', N'          ', 0)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP01', N'Cafe Sữa', 25000, N'cafesua.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP02', N'Cafe Đen', 25000, N'cafeden.jpg', 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP03', N'Trà ĐÀo', 35000, N'daossua.jpg', 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP04', N'Trà Chanh', 25000, NULL, 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP05', N'Trà gừng', 25000, NULL, 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP06', N'Trà đào cam xả', 35000, NULL, 1, 2)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP07', N'Bạc xỉu đá', 25000, NULL, 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP08', N'Bạc xỉu nóng', 25000, NULL, 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP09', N'Cafe Kem', 30000, NULL, 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP10', N'Cafe Cốt Dừa', 30000, NULL, 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP11', N'Cafe Đá Xay', 25000, NULL, 1, 1)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP12', N'Trà sữa Dâu Tây', 35000, NULL, 1, 4)
INSERT [dbo].[Product] ([IDProduct], [ProductName], [Price], [Image], [Status], [IDType]) VALUES (N'SP13', N'Trà sữa Dâu Tây', 35000, NULL, 1, 3)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (1, N'Cafe', NULL)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (2, N'Trà', NULL)
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (3, N'Trà sữa', N'L')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (4, N'Trà sữa', N'M')
INSERT [dbo].[ProductType] ([IDType], [TypeName], [Size]) VALUES (5, N'Nước ép', NULL)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB001', 12, 1, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB002', 2, 1, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB003', 3, 1, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB004', 4, 0, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB005', 3, 1, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB006', 14, 0, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB007', 5, 0, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB008', 6, 0, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB009', 7, 0, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB010', 8, 0, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB011', 9, 1, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB012', 10, 1, 1)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB013', 13, 0, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB014', 5, 0, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB015', 7, 0, 2)
INSERT [dbo].[Table] ([IDTable], [Location], [Status], [IDArea]) VALUES (N'TB016', 2, 1, 2)
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
ALTER TABLE [dbo].[Table]  WITH CHECK ADD  CONSTRAINT [FK_Table_Area] FOREIGN KEY([IDArea])
REFERENCES [dbo].[Area] ([IDArea])
GO
ALTER TABLE [dbo].[Table] CHECK CONSTRAINT [FK_Table_Area]
GO
