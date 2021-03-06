USE cigou
GO
/****** Object:  Table dbo.wh_order_header    Script Date: 04/01/2016 15:35:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE dbo.wh_order_header(
	order_id varchar(50) NOT NULL,
	orderDate varchar(19) NOT NULL,
	customerId varchar(20) NOT NULL,
	packingMaterial varchar(50) NOT NULL,
	warehouseId varchar(50) NOT NULL,
	tpl varchar(50) NOT NULL,
	orderType int NOT NULL,
	electriccode varchar(64) NOT NULL,
	deliveryCode varchar(64) NOT NULL,
	notes varchar(2000) NOT NULL,
	cbepcomcode varchar(64) NOT NULL,
 CONSTRAINT PK_wh_order_header PRIMARY KEY CLUSTERED 
(
	order_id ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON PRIMARY
) ON PRIMARY

GO
SET ANSI_PADDING OFF