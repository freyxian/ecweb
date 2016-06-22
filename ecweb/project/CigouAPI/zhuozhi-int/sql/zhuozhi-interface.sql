
-- send order info to zhuozhi
select * from wh_order_header
-- update wh_order_header set orderDate='2016-4-11 12:34:00'
-- update wh_order_header set customerId='suntech ???', warehouseId='CA-TO-6324', electriccode='suntech ??????'
-- update wh_order_header set packingMaterial='barrel'
-- update wh_order_header set notes='2016-4-16'
where
  order_id in ( '00043473811', '00043473809' )
;

-- delete from wh_order_confirm;

select * from wh_order_confirm
-- delete from wh_order_confirm
where
  order_id in ( '00043473811', '00043473809' )
;

commit;


 select order_id from wh_order_header oh where 
-- can be change in whereClause zhuozhi.xml
 not exists ( select 1 from wh_order_confirm c where c.order_id = oh.order_id )
;


-- zhuozhi info push back: goods entry, delivery, logistics status
select * from wh_order_delivery
-- delete from wh_order_delivery
where 
	enOrderCode='00462346112'
;


select * from wh_goods_entry
-- delete from wh_goods_entry
where
	order_no='00462346112'
;

select * from wh_order_logistics
-- delete from wh_order_logistics
where
	enOrderCode='00462346112'
;

select * from wh_order_logistics_flow
-- delete from wh_order_logistics_flow
where
	order_id='00462346112'
;

commit;




