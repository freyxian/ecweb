USE cigou
GO
/****** Object:  Table dbo.wh_order_items    Script Date: 04/01/2016 15:36:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE dbo.wh_order_items(
	good_id varchar(20) NOT NULL,
	amount int NOT NULL,
	price float NOT NULL,
	order_id varchar(50) NOT NULL
) ON PRIMARY

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE dbo.wh_order_items  WITH CHECK ADD FOREIGN KEY(order_id)
REFERENCES dbo.wh_order_header (order_id)