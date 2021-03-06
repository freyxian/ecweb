USE cigou
GO
/****** Object:  Table dbo.wh_order_recipient    Script Date: 04/01/2016 15:36:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE dbo.wh_order_recipient(
	order_id varchar(50) NOT NULL,
	name varchar(20) NOT NULL,
	receive_type int NULL,
	receive_no varchar(30) NOT NULL,
	mobile_phone varchar(20) NOT NULL,
	phone varchar(10) NOT NULL,
	country varchar(10) NOT NULL,
	province varchar(10) NOT NULL,
	city varchar(10) NOT NULL,
	district varchar(10) NOT NULL,
	address varchar(250) NOT NULL,
	postcode varchar(10) NOT NULL
) ON PRIMARY

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE dbo.wh_order_recipient  WITH CHECK ADD FOREIGN KEY(order_id)
REFERENCES dbo.wh_order_header (order_id)