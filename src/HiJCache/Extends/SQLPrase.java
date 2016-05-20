package HiJCache.Extends;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import HiJCache.IParse;

/**
 * 解析XML
 * @author 徐敏荣
 *
 */
final class SQLPrase implements IParse<SQLInfo> {

	/* (non-Javadoc)
	 * @see HiJCache.IParse#Parse(org.w3c.dom.Element)
	 */
	@Override
	public SQLInfo Parse(Element node) {
		SQLInfo info = new SQLInfo();
		NodeList nodes = node.getChildNodes(); 
		if (nodes == null || nodes.getLength() < 1) {
			return null;
		}		

		String dbtype = node.getAttribute("type");
		if (dbtype == null || dbtype.trim().equals("")) {
			return null;
		}
		info.setDBType(dbtype.toLowerCase());
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element item = (Element)child;
			if (item.getNodeName().toLowerCase().equals("text")) {
				info.setSQL(item.getTextContent());
			}
			if (item.getNodeName().toLowerCase().equals("countsql")) {
				info.setCountSQL(item.getTextContent());
			}
			if (item.getNodeName().toLowerCase().equals("paramers")) {
				ParaseParamers(item, info);
			}
		}
		
		if (info.getSQL() == null || info.getSQL().trim() == "") {
			return null;
		}
		return info;
	}
	
	/**
	 * 解析SQ参数
	 * @param node
	 * @param info
	 */
	private void ParaseParamers(Element node, SQLInfo info) {
		NodeList nodes = node.getChildNodes(); 
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element item = (Element)child;
			if (!item.getNodeName().toLowerCase().equals("paramer")) {
				continue;
			}
			String name = item.getAttribute("name");
			String value = item.getAttribute("value");
			if (name == null || value == null) {
				continue;
			}
			SQLParamer paramer = new SQLParamer();
			paramer.setName(name);
			paramer.setValue(value);
			info.getParams().add(paramer);
		}
	}

}
