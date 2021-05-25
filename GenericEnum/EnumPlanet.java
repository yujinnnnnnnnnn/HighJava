package kr.or.ddit.basic;

public class EnumPlanet {

	public enum Planet{ 수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	
		private int inte;
		
		Planet(int data){
			this.inte= data;
		}
		public int getinte() {
			return inte;
		}
	}
	public static void main(String[] args) {
		Planet[] planArr = Planet.values();
		for(Planet plan : planArr) {
			System.out.println(plan + " : " + (double)(4*3.14*Math.pow(plan.getinte(), 2)));
		}
	}
}
