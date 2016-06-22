package test;

import static org.junit.Assert.*;

import org.junit.Test;

import CigouDAO.CDAO.OrderFetch;
import CigouDAO.CDAO.WholeOrder;

public class OrderFetchTest {

	@Test
	public void test() {
		OrderFetch of= new OrderFetch();
		WholeOrder wo=of.fetchWholeOrder("00043473811");
		System.out.println(wo.getHeader().getCustomerId());
		System.out.println(wo.getItems().toString());
		fail("Not yet implemented");
	}

}
