package HiJUtil.Test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import HiJUtil.HiCBO;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEventRet8Param;

public class TestHiCBO {
	@Test
	public void Test_CBO() {
		TestObj obj = new TestObj();
		boolean ret = HiCBO.FillObject(obj, TestObj.class, new IEventRet8Param<String, String>(){

			@Override
			public String OnEvent(String v) {
				if (v.equals("X")) {
					return "test";
				}
				if (v.equals("Y")) {
					return "3";
				}
				if (v.equals("Z")) { 
					return "2.7";
				}
				if (v.equals("Date")) {
					return "2016-05-19";
				}
				if (v.equals("Date2")) {
					return "2016-05-19";
				}
				if (v.equals("Date3")) {
					return "2016-05-19 14:03:14";
				}
				if (v.equals("Date4")) {
					return "2016-05-19 02:03:14";
				}
				return null;
			}
			
		});
		Assert.assertTrue(ret);
		Assert.assertEquals(obj.getX(), "test");
		Assert.assertEquals(obj.getY(), 3);
		Assert.assertEquals(obj.getY2(), -1);
		Assert.assertTrue(obj.getZ() == 2.7f);
		Assert.assertEquals(obj.getDate(), HiTypeHelper.Convert2Date("2016-05-19"));
		Assert.assertEquals(obj.getDate2(), HiTypeHelper.Convert2SqlDate("2016-05-19"));
		Assert.assertEquals(obj.getDate3(), HiTypeHelper.Convert2Date("2016-05-19 14:03:14"));
		Assert.assertEquals(obj.getDate4(), HiTypeHelper.Convert2SqlDate("2016-05-19 02:03:14"));
		Assert.assertEquals(obj.getDate5(), null);
	}

	@Test
	public void Test_CBOEx() {
		TestObj obj = new TestObj();
		boolean ret = HiCBO.FillObjectEx(obj, TestObj.class, new IEventRet8Param<Object, String>(){

			@Override
			public Object OnEvent(String v) {
				if (v.equals("X")) {
					return "test";
				}
				if (v.equals("Y")) {
					return 3;
				}
				if (v.equals("Z")) { 
					return 2.7;
				}
				if (v.equals("Date")) {
					return "2016-05-19";
				}
				if (v.equals("Date2")) {
					return "2016-05-19";
				}
				if (v.equals("Date3")) {
					return "2016-05-19 14:03:14";
				}
				if (v.equals("Date4")) {
					return "2016-05-19 02:03:14";
				}
				return null;
			}
			
		});
		Assert.assertTrue(ret);
		Assert.assertEquals(obj.getX(), "test");
		Assert.assertEquals(obj.getY(), 3);
		Assert.assertEquals(obj.getY2(), -1);
		Assert.assertTrue(obj.getZ() == 2.7f);
		Assert.assertEquals(obj.getDate(), HiTypeHelper.Convert2Date("2016-05-19"));
		Assert.assertEquals(obj.getDate2(), HiTypeHelper.Convert2SqlDate("2016-05-19"));
		Assert.assertEquals(obj.getDate3(), HiTypeHelper.Convert2Date("2016-05-19 14:03:14"));
		Assert.assertEquals(obj.getDate4(), HiTypeHelper.Convert2SqlDate("2016-05-19 02:03:14"));
		Assert.assertEquals(obj.getDate5(), null);
	}
	
	@Test
	public void Test_Date2String() {
		java.sql.Date dt = HiTypeHelper.Convert2SqlDate("2016-5-19 2:3:14");
		String dtStr = HiTypeHelper.ToLongString(dt);

		Assert.assertEquals(dtStr, "2016-05-19 02:03:14");
		dtStr = HiTypeHelper.ToShortString(dt);

		Assert.assertEquals(dtStr, "2016-05-19");
		

		java.util.Date dt2 = HiTypeHelper.Convert2Date("2016-5-19 2:3:14");
		String dtStr2 = HiTypeHelper.ToLongString(dt2);

		Assert.assertEquals(dtStr2, "2016-05-19 02:03:14");
		dtStr2 = HiTypeHelper.ToShortString(dt2);

		Assert.assertEquals(dtStr2, "2016-05-19");
	}
	
	enum EnumTest
	{
		x,
		y
	}
	@Test
	public void Test_ValueType() {
		Class<?> up = Number.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Integer.class.getSuperclass();
		Assert.assertNotNull(up);
		up = String.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Float.class.getSuperclass();
		Assert.assertNotNull(up);
		up = char.class.getSuperclass();
		Assert.assertNull(up);
		up = int.class.getSuperclass();
		Assert.assertNull(up);
		up = float.class.getSuperclass();
		Assert.assertNull(up);
		up = EnumTest.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Enum.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Object.class.getSuperclass();
		Assert.assertNull(up);
	}

	@Test
	public void Test_Cast() {
		int x = Integer.class.cast(-1);
		Assert.assertEquals(x, -1);
	}
	@Test
	public void Test_HiTypeHelper_Cast() {
		Integer val = 1;
		int ret = HiTypeHelper.Cast(int.class, val);
		Assert.assertEquals(ret, 1);
		String text = "1";
		ret = HiTypeHelper.Cast(int.class, text);
		Assert.assertEquals(ret, 1);
		text = "a";
		ret = HiTypeHelper.Cast(int.class, text);
		Assert.assertEquals(ret, -1);
		int val2 = 1;
		Integer ret2 = HiTypeHelper.Cast(Integer.class, val2);
		Assert.assertEquals(ret2.intValue(), 1);
		text = "1";
		ret2 = HiTypeHelper.Cast(Integer.class, text);
		Assert.assertEquals(ret2.intValue(), 1);
		boolean bl = HiTypeHelper.Cast(boolean.class, 1);
		Assert.assertTrue(bl);
		bl = HiTypeHelper.Cast(boolean.class, 3);
		Assert.assertTrue(bl);
		bl = HiTypeHelper.Cast(boolean.class, 0);
		Assert.assertFalse(bl);
		bl = HiTypeHelper.Cast(boolean.class, -1);
		Assert.assertFalse(bl); 
		bl = HiTypeHelper.Cast(boolean.class, "1");
		Assert.assertTrue(bl);
		bl = HiTypeHelper.Cast(boolean.class, "3");
		Assert.assertTrue(bl);
		bl = HiTypeHelper.Cast(boolean.class, "0");
		Assert.assertFalse(bl);
		bl = HiTypeHelper.Cast(boolean.class, "-1");
		Assert.assertFalse(bl);
	}
	@Test
	public void Test_HiTypeHelper_Number() {
		Assert.assertTrue(HiTypeHelper.IsNumer(int.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(BigDecimal.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(float.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(double.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(long.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(short.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(boolean.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(char.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(Float.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(Double.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(Long.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(Short.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(byte.class));
		Assert.assertTrue(HiTypeHelper.IsNumer(Byte.class));
		Assert.assertFalse(HiTypeHelper.IsNumer(Boolean.class));
		Assert.assertFalse(HiTypeHelper.IsNumer(void.class));
		Assert.assertFalse(HiTypeHelper.IsNumer(Void.class));
		Assert.assertFalse(HiTypeHelper.IsNumer(EnumTest.class));
		Assert.assertFalse(HiTypeHelper.IsNumer(Object.class));
	}

	@Test
	public void Test_HiTypeHelper_GetDefault() {
		BigDecimal val = HiTypeHelper.GetDefault(BigDecimal.class);
		Assert.assertEquals(val, new BigDecimal(-1));
		int val2 = HiTypeHelper.GetDefault(int.class);
		Assert.assertEquals(val2, -1);
		Integer val3 = HiTypeHelper.GetDefault(Integer.class);
		Assert.assertEquals(val3, new Integer(-1));
		boolean bl = HiTypeHelper.GetDefault(boolean.class);
		Assert.assertFalse(bl);
		Boolean bl2 = HiTypeHelper.GetDefault(Boolean.class);
		Assert.assertFalse(bl2);
	}
	@Test
	public void Test_ToString() {
		TestObj obj = new TestObj();
		obj.setX("test");
		obj.setY(1);
		obj.setDate2(null);
		String str = HiTypeHelper.ToString(TestObj.class, obj);
		Assert.assertTrue(str != null);
		System.out.print(str);
	}
}
