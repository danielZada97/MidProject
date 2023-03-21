package Junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import methods.IMoney;
import methods.Money;
import methods.MoneyBag;

public class MoneyBagTest extends TestCase {

	private IMoney expected;
	private IMoney result;
	private Money m10nis;
	private Money m20nis;
	private Money m30nis;
	private Money m40nis;
	private Money m10usd;
	private Money m10chf;
	private Money minus10nis;
	private Money m50nis;
	private MoneyBag testbag;
	private Money m24nis;

	@Before
	public void setUp() throws Exception {
		expected = new MoneyBag();
		result = new MoneyBag();
		m10nis = new Money(10, "NIS");
		m20nis = new Money(20, "NIS");
		m30nis = new Money(30, "NIS");
		m40nis = new Money(40, "NIS");
		m50nis = new Money(50, "NIS");
		minus10nis = new Money(-10, "NIS");
		m10usd = new Money(10, "USD");
		m10chf = new Money(10, "CHF");
		testbag = new MoneyBag(m20nis, m10usd);
		m24nis=new Money(24,"NIS");

	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Functionality check: Testing adding the same type of money to MoneyBag Input
	 * data: MoneyBag: 20 NIS, 30 NIS Expected result: adding 10 NIS to MoneyBag:
	 * 20NIS , 20NIS
	 */
	@Test
	public void testAddMoney_addSameCurrency() {
		expected = new MoneyBag(m30nis,m10usd);
		result = new MoneyBag(m20nis,m10usd);
		result = result.addMoney(m10nis);
		assertEquals(result, expected);//true

	}

	/*
	 * Functionality check: Testing adding the same type of money to MoneyBag Input
	 * data: MoneyBag: 20 NIS, 30 NIS Expected result: adding 10 NIS to MoneyBag:
	 * 20NIS , 20NIS
	 */
	@Test
	public void testAddMoney_substractSameCurrency() {
		expected = new MoneyBag(m10nis, m10usd);
		result = new MoneyBag(m20nis, m10usd);
		result = result.addMoney(minus10nis);
		assertEquals(result, expected);

	}
	@Test
	public void testAddMoney_add_10_and_14_get_24() {
		expected =new MoneyBag(m24nis,m10usd);;
		result =  new MoneyBag(m10nis,m10usd);
		result = result.addMoney( new Money(14,"NIS"));
		assertEquals(result, expected);

	}
	
	/*
	 * Functionality check: Testing adding the same type of money to MoneyBag Input
	 * data: MoneyBag: 20 NIS, 30 NIS, 10USD Expected result: adding 10 USD to
	 * MoneyBag: 20NIS , 30 NIS
	 */
	@Test
	public void testAddMoney_add_differentCurrency() {
		expected = new MoneyBag(new Money[] { m20nis, m30nis, m10usd });
		result = new MoneyBag(m20nis, m30nis);
		result = result.addMoney(m10usd);
		assertEquals(result, expected);

	}

	/*
	 * Functionality check: Testing adding the same type of money to MoneyBag Input
	 * data: MoneyBag: 20 NIS, 30 NIS Expected result: adding 10 NIS to MoneyBag:
	 * 20NIS , 20NIS
	 */
	@Test
	public void testAddMoney_addnull() {

		result = new MoneyBag(m20nis, m20nis);
		try {
			result = result.addMoney(null);
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}

	}

	@Test
	public void testContains_sameamountandcurrency() {
		assertTrue(testbag.contains(m20nis));
	}

	@Test
	public void testContains_DifferentAmountSameCurrency() {
		assertFalse(testbag.contains(m50nis));
	}

	@Test
	public void testContains_SameAmountDifferentCurrency() {
		try {
			assertFalse(testbag.contains(m10chf));
		} catch (NullPointerException e) {

		}
	}

	@Test
	public void testContains_DifferentAmountDifferentCurrency() {
		try {
			assertFalse(testbag.contains(new Money(100, "USD")));
		} catch (NullPointerException e) {

		}
	}

}
