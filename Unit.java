package test;

import java.util.HashMap;
import java.util.Map;

public class Unit {

	private Double number;
	private String single;
	private String plural;
	public static Map<Unit, Double> rateList;

	public static Map<String, String> singleToPlural;
	public static Map<String, String> pluralToSingle;
	static {
		rateList = new HashMap<Unit, Double>();
		singleToPlural = new HashMap<String, String>();
		pluralToSingle = new HashMap<String, String>();
		singleToPlural.put("mile", "miles");
		singleToPlural.put("yard", "yards");
		singleToPlural.put("inch", "inches");
		singleToPlural.put("foot", "feet");
		singleToPlural.put("fath", "faths");
		singleToPlural.put("furlong", "furlongs");
		pluralToSingle.put("miles", "mile");
		pluralToSingle.put("yards", "yard");
		pluralToSingle.put("inches", "inch");
		pluralToSingle.put("feet", "foot");
		pluralToSingle.put("faths", "fath");
		pluralToSingle.put("furlongs", "furlong");
	}

	public Unit() {

	}

	public Unit(Double number, String single, String plural) {
		this.number = number;
		this.single = single;
		this.plural = plural;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

	public String toString() {
		return "number:" + number + "\tsingle:" + single + "\tplural:" + plural
				+ "\tmeter:" + toMeter();
	}

	public Double toMeter() {
		for (Unit u : Unit.rateList.keySet()) {
			if (u.single.equals(single) && u.plural.equals(plural)) {
				return this.number * rateList.get(u);
			}
		}
		return 0.0;
	}
}
