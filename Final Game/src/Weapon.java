public class Weapon
{		
	public static String[][] weaponClass =	{{"Brave Bow","Superior Bow","Silver Bow","Killer Bow"," Hunter's Bow","Iron Bow","Bronze Bow"}
											,{"Brave Sword","Superior Edge","Silver Sword","Killer Edge","Armorslayer","Iron Sword","Bronze Sword"}
											,{"Brave Axe","Superior Axe","Silver Axe","Killer Axe","Volant Axe","Iron Axe","Hammer","Bronze Axe"}
											,{"Brave Lance","Superior Lance","Silver Lance","Killer Lance","Beast Killer","Iron Lance","Bronze Lance"}
											,{"Whirlwind","Superior Jolt","Thunder","Light Strike","Gale","Bolt","Arc","Spark"}};

	public int[][] allMights = 		{{ 8, 13, 13, 8, 6, 8, 6} //bows
									,{ 6, 11, 11, 6, 8, 6, 4} //sword
									,{ 8, 15, 15, 8, 8, 8, 10, 6} //axe
									,{ 7, 13, 13, 7, 9, 7, 5} //lance
									,{ 6, 11, 11, 6, 8, 6, 10, 4}}; //tome
	
	public int[][] allHits = 		{{65, 70, 75, 75, 85, 80, 90} //bows
									,{75, 80, 85, 85, 80, 90, 99} //sword
									,{55, 60, 65, 65, 55, 70, 60, 80} //axe
									,{65, 70, 75, 75, 70, 80, 90} //lance
									,{75, 80, 85, 85, 80, 90, 60, 99}}; //tome
	
	public int[][] allCriticals = 	{{ 0, 0, 0, 25, 0, 0, 0} // bows
									,{ 0, 0, 0, 25, 0, 0, 0} // sword
									,{ 0, 0, 0, 25, 0, 0, 0, 0} //axe
									,{ 0, 0, 0, 25, 0, 0, 0} // lance
									,{ 0, 0, 0, 25, 0, 0, 0, 0}}; //tome
	
	public String[][] allEffects = 	{{"Brave","Superior","Default","Critical","Anti-Beast","Default","Default"} //all bows are anti flyer as well
									,{"Brave","Superior","Default","Critical","Piercing","Default","Default"}
									,{"Brave","Superior","Default","Critical","Anti-Flyer","Default","Piercing","Default"}
									,{"Brave","Superior","Default","Critical","Anti-Beast","Default","Default"}
									,{"Brave","Superior","Default","Critical","Anti-Flyer","Default","Default","Default"}};
	public String[] allClasses = {"Bow","Sword","Axe","Lance","Tome"};
	
	private String chosenWeaponName = null;
	private int chosenWeaponMight = 0;
	private int chosenWeaponCritical = 0;
	private int chosenWeaponHit = 0;
	//public static char chosenWeaponRank ;
	private String chosenWeaponEffect  = null;
	private String chosenWeaponClass  = null;
	
	public Weapon(String inputUser)
	{
		for(int i = 0; i < weaponClass.length; i++)
		{
			for(int j = 0; j < weaponClass[i].length; j++)
			{
				if ( inputUser.equals(weaponClass[i][j]))
				{
					setChosenWeaponName(inputUser);
					setChosenWeaponMight(allMights[i][j]);
					setChosenWeaponCritical(allCriticals[i][j]);
					setChosenWeaponHit(allHits[i][j]);
					setChosenWeaponEffect(allEffects[i][j]);
					setChosenWeaponClass(allClasses[i]);
				}
			}
		}
	}

	public String getChosenWeaponName()
	{
		return chosenWeaponName;
	}

	public void setChosenWeaponName(String chosenWeaponName)
	{
		this.chosenWeaponName = chosenWeaponName;
	}

	public int getChosenWeaponHit()
	{
		return chosenWeaponHit;
	}

	public void setChosenWeaponHit(int chosenWeaponHit)
	{
		this.chosenWeaponHit = chosenWeaponHit;
	}

	public int getChosenWeaponMight()
	{
		return chosenWeaponMight;
	}

	public void setChosenWeaponMight(int chosenWeaponMight)
	{
		this.chosenWeaponMight = chosenWeaponMight;
	}

	public int getChosenWeaponCritical()
	{
		return chosenWeaponCritical;
	}

	public void setChosenWeaponCritical(int chosenWeaponCritical)
	{
		this.chosenWeaponCritical = chosenWeaponCritical;
	}

	public String getChosenWeaponEffect()
	{
		return chosenWeaponEffect;
	}

	public void setChosenWeaponEffect(String chosenWeaponEffect)
	{
		this.chosenWeaponEffect = chosenWeaponEffect;
	}

	public String getChosenWeaponClass()
	{
		return chosenWeaponClass;
	}

	public void setChosenWeaponClass(String chosenWeaponClass)
	{
		this.chosenWeaponClass = chosenWeaponClass;
	}
}
