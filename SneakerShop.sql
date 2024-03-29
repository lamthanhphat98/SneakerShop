USE [master]
GO
/****** Object:  Database [SneakerShop]    Script Date: 10/17/2019 12:55:23 PM ******/
CREATE DATABASE [SneakerShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SneakerShop', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\SneakerShop.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SneakerShop_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\SneakerShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SneakerShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SneakerShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SneakerShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SneakerShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SneakerShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SneakerShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SneakerShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [SneakerShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SneakerShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SneakerShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SneakerShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SneakerShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SneakerShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SneakerShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SneakerShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SneakerShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SneakerShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SneakerShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SneakerShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SneakerShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SneakerShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SneakerShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SneakerShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SneakerShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SneakerShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SneakerShop] SET  MULTI_USER 
GO
ALTER DATABASE [SneakerShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SneakerShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SneakerShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SneakerShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SneakerShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [SneakerShop]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NULL,
	[Owner] [nvarchar](max) NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Cart]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[CartId] [int] IDENTITY(1,1) NOT NULL,
	[ProductId] [int] NOT NULL,
	[ProductName] [nvarchar](max) NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[SizeName] [nvarchar](50) NOT NULL,
	[Picture] [nvarchar](max) NOT NULL,
	[UserId] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tbl_Cart] PRIMARY KEY CLUSTERED 
(
	[CartId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ShopUrl] [nvarchar](250) NOT NULL,
	[Crawl] [int] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ShopUrl] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[History]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[Username] [nvarchar](50) NOT NULL,
	[ShoesId] [int] NOT NULL,
 CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED 
(
	[Username] ASC,
	[ShoesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Order]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [nvarchar](50) NOT NULL,
	[OrderDate] [date] NULL,
 CONSTRAINT [PK_tbl_Order] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 10/17/2019 12:55:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NOT NULL,
	[ProductId] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[SizeName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Shoes]    Script Date: 10/17/2019 12:55:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shoes](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](250) NULL,
	[Price] [float] NULL,
	[Picture] [nvarchar](max) NULL,
	[Sale] [bit] NULL,
	[Quantity] [int] NULL,
	[SelledBy] [nvarchar](250) NULL,
	[Viewed] [int] NULL,
 CONSTRAINT [PK_tbl_Boots] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Trend]    Script Date: 10/17/2019 12:55:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Trend](
	[Username] [nvarchar](50) NOT NULL,
	[SearchValue] [nvarchar](max) NULL,
 CONSTRAINT [PK_Trend] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 10/17/2019 12:55:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[Phone] [nvarchar](50) NULL,
	[Address] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cart_tbl_Boots] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Shoes] ([Id])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_tbl_Cart_tbl_Boots]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cart_tbl_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_tbl_Cart_tbl_User]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Shoes] FOREIGN KEY([ShoesId])
REFERENCES [dbo].[Shoes] ([Id])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Shoes]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_User] FOREIGN KEY([Username])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_User]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_User]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetails_tbl_Boots] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Shoes] ([Id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetails_tbl_Boots]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetails_tbl_Order] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Order] ([OrderId])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetails_tbl_Order]
GO
ALTER TABLE [dbo].[Shoes]  WITH CHECK ADD  CONSTRAINT [FK_Shoes_Category] FOREIGN KEY([SelledBy])
REFERENCES [dbo].[Category] ([ShopUrl])
GO
ALTER TABLE [dbo].[Shoes] CHECK CONSTRAINT [FK_Shoes_Category]
GO
ALTER TABLE [dbo].[Trend]  WITH CHECK ADD  CONSTRAINT [FK_Trend_User] FOREIGN KEY([Username])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[Trend] CHECK CONSTRAINT [FK_Trend_User]
GO
USE [master]
GO
ALTER DATABASE [SneakerShop] SET  READ_WRITE 
GO
