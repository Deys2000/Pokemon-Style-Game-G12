public class Character
{
	public String name;
	public int placement;
	
	public int characterColumn = 0; //determines which column to use
	
	//archer, cavalier, fighter,knight, mage, myrmidon, pegasus knight, thief, wyvern rider, dark mage
	//health,
	private double baseStatistics[][] = 	{{17,17,19,18,15,16,18,16,19,16}
										,{05,06,07, 8,00,04,06,03,07,00}
										,{00,00,00,00,04,01,01,00,00,06}
										,{07,05,06,04,05, 9,07,06,06,03}
										,{05,05,06,03,06,10,10, 8,05,03}
										,{02,03,02,00,01,00,05,00,00,01}
										,{04,05,04,07,01,04,04,02, 8,03}
										,{01,03,01,00,03,01, 9,00,00,05}};
	
	private double baseGrowths [][] = 	{{55,55,55,60,45,50,50,45,55,60}
										,{25,25,35,35,10,30,20,25,40,15}
										,{20,10,10,10,30,10,10,15,10,25}
										,{40,25,30,25,30,25,25,35,25,25}
										,{25,25,25,20,30,35,20,35,35,25}
										,{15,25,10,10,10,25,30,10,10,10}
										,{20,20,20,25,15,15,10,25,20,20}
										,{15,15,15,15,20,15,30,15,15,20}};
	private String unitTypes[] = {"I","M","I","A","I","I","FM","I","FM","I"};
	
	public double baseHealthGrowth;
	public double baseStrengthGrowth;
	public double baseMagicGrowth;
	public double baseSkillGrowth;
	public double baseSpeedGrowth;
	public double baseLuckGrowth;
	public double baseDefenseGrowth;
	public double baseResistanceGrowth;
	
	public double healthGrowth;
	public double strengthGrowth;
	public double magicGrowth;
	public double skillGrowth;
	public double speedGrowth;
	public double luckGrowth;
	public double defenseGrowth;
	public double resistanceGrowth;
	
	public double baseHealth;
	public double baseStrength;
	public double baseMagic;
	public double baseSkill;
	public double baseSpeed;
	public double baseLuck;
	public double baseDefense;
	public double baseResistance;
	
	public double combatHealth;
	public double combatStrength;
	public double combatMagic;
	public double combatSkill;
	public double combatSpeed;
	public double combatLuck;
	public double combatDefense;
	public double combatResistance;
	
	public String weaponName;
	public String weaponClass;
	public double weaponHit;
	public double weaponMight;
	public double weaponCritical;
	public String weaponEffect;
	
	public double hitChance;
	public double critChance;
	public double avoidChance;
	public double dodgeChance;
	
	public double damageMultiplier = 1;
	public double damageAdder = 0;
	
	public double  healthRegainAmount = 0;
	public boolean healthRegain = false;
	public boolean critWorked = false;
	public boolean hitWorked = false;
	
	public double damage = 0;

	public String unitType;
	
	public int healthPercentage; //needs to be int to show health bar
	
	//String attribute[] = new String[5];			//contains base atk,def,spd and luck
	public String skillNameAndDescription[][] = new String[3][4];	//contains string versions of atk names and also descriptions
	//public int[] attackDmg = new int[4];		//contains integers of base attack damages
	
	Character(String characterType)
	{
		name = characterType;
		if(name.equals("Hati")|| name.equals("Nidhogg") || name.equals("Hábrók")||name.equals("Surt"))
		{
			determineHardCharStats(name);
		}
		else
		{
			determineStats(name);		
			hitChance = ((combatSkill*3) + (combatLuck/2)) + weaponHit;
			critChance = (combatSkill/2) + weaponCritical;
			avoidChance = ((combatSpeed * 3)+combatLuck)/2;
			dodgeChance = combatLuck;
		}	
		healthPercentage = (int)(((combatHealth*100)/baseHealth)+.5);
	}
	
	
	private void determineHardCharStats(String premadeChar)
	{
		if(premadeChar.equals("Hati"))
		{
			unitType = "B";

			baseHealth = 	combatHealth =		44;
			baseStrength=	combatStrength =	33;
			baseMagic=		combatMagic =		0;
			baseSkill=		combatSkill = 		26;
			baseSpeed=		combatSpeed = 		27;
			baseLuck=		combatLuck = 		13;
			baseDefense=	combatDefense = 	20;
			baseResistance=	combatResistance = 	5;		
			
			hitChance = 165;
			critChance = 13;
			avoidChance = 47;
			dodgeChance = 13;
	
			//name of each attack 		
			skillNameAndDescription[0][0] = "Odd Shaped";
			skillNameAndDescription[0][1] = "Great Hunter";
			skillNameAndDescription[0][2] = "Regeneration";
			skillNameAndDescription[0][3] = "Lunacy";
			
			skillNameAndDescription[1][0] = "Damage +4 on odd rounds";
			skillNameAndDescription[1][1] = "Attacks have Anti-Beast Effect";
			skillNameAndDescription[1][2] = "Heal 18 Health every odd round";
			skillNameAndDescription[1][3] = "26% chance to activate Luna";
			
			skillNameAndDescription[2][0] = "";
			skillNameAndDescription[2][1] = "";
			skillNameAndDescription[2][2] = "";
			skillNameAndDescription[2][3] = "";
			
		}
		else if(premadeChar.equals("Hábrók"))
		{
			unitType = "BF";

			baseHealth = 	combatHealth =		38;
			baseStrength=	combatStrength =	20;
			baseMagic=		combatMagic =		0;
			baseSkill=		combatSkill = 		32;
			baseSpeed=		combatSpeed = 		32;
			baseLuck=		combatLuck = 		19;
			baseDefense=	combatDefense = 	14;
			baseResistance=	combatResistance = 	10;		
			
			hitChance = 186;
			critChance = 16;
			avoidChance = 58;
			dodgeChance = 19;
	
			//name of each attack 		
			skillNameAndDescription[0][0] = "Even Rhythm";
			skillNameAndDescription[0][1] = "Flying Ace";
			skillNameAndDescription[0][2] = "Regeneration";
			skillNameAndDescription[0][3] = "Cyclone";
			
			skillNameAndDescription[1][0] = "Hit and Avoid +10 on even rounds";
			skillNameAndDescription[1][1] = "Attacks have Anti-Flyer Effect";
			skillNameAndDescription[1][2] = "Heal 15 Health every even round";
			skillNameAndDescription[1][3] = "16% chance to trigger Astra";
			
			skillNameAndDescription[2][0] = "";
			skillNameAndDescription[2][1] = "";
			skillNameAndDescription[2][2] = "";
			skillNameAndDescription[2][3] = "";
		}
		else if(premadeChar.equals("Nidhogg")) 
		{
			unitType = "BA";

			baseHealth = 	combatHealth =		38;
			baseStrength=	combatStrength =	30;
			baseMagic=		combatMagic =		6;
			baseSkill=		combatSkill = 		18;
			baseSpeed=		combatSpeed = 		8;
			baseLuck=		combatLuck = 		9;
			baseDefense=	combatDefense = 	37;
			baseResistance=	combatResistance = 	27;		
			
			hitChance = 139;
			critChance = 9;
			avoidChance = 17;
			
			dodgeChance = 9;
	
			//name of each attack 		
			skillNameAndDescription[0][0] = "Odd Scales";
			skillNameAndDescription[0][1] = "Razor Claws";
			skillNameAndDescription[0][2] = "Regeneration";
			skillNameAndDescription[0][3] = "Dragon Scales";
			
			skillNameAndDescription[1][0] = "Damage received -4 on odd rounds";
			skillNameAndDescription[1][1] = "Attacks have Armor Piercing Effect";
			skillNameAndDescription[1][2] = "Heal 15 Health every odd round";
			skillNameAndDescription[1][3] = "18% chance to trigger Heavily Armored";
			
			skillNameAndDescription[2][0] = "";
			skillNameAndDescription[2][1] = "";
			skillNameAndDescription[2][2] = "";
			skillNameAndDescription[2][3] = "";
			
		}
		else if(premadeChar.equals("Surt"))
		{
			unitType = "BF";

			baseHealth = 	combatHealth =		38;
			baseStrength=	combatStrength =	26;
			baseMagic=		combatMagic =		28;
			baseSkill=		combatSkill = 		21;
			baseSpeed=		combatSpeed = 		14;
			baseLuck=		combatLuck = 		3;
			baseDefense=	combatDefense = 	22;
			baseResistance=	combatResistance = 	21;		
			
			hitChance = 156;
			critChance = 11;
			avoidChance = 24;
			dodgeChance = 6;
	
			//name of each attack 		
			skillNameAndDescription[0][0] = "Slow Burn";
			skillNameAndDescription[0][1] = "Vampiric Blade";
			skillNameAndDescription[0][2] = "Vengeance+";
			skillNameAndDescription[0][3] = "Ignis";
			
			skillNameAndDescription[1][0] = "Hit and Avoid +1 each  round";
			skillNameAndDescription[1][1] = "-10 Hit but heal for half damage dealt";
			skillNameAndDescription[1][2] = "-5 Hit but deal bonus damage equal to half missing Health";
			skillNameAndDescription[1][3] = "21% chance to add MAG/2 or STR/2 damage to attacks";
			
			skillNameAndDescription[2][0] = "";
			skillNameAndDescription[2][1] = "";
			skillNameAndDescription[2][2] = "";
			skillNameAndDescription[2][3] = "";
			
		}		
	}


	public void determineStats(String name)
	{
		 if(name.equals("Archer"))
		 {	 
		 characterColumn= 0;
		 }
		 if(name.equals("Cavalier"))
		 {	 
		 characterColumn= 1;
		 }
		 if(name.equals("Fighter"))
		 {	 
		 characterColumn= 2;
		 }
		 if(name.equals("Knight"))
		 {	 
		 characterColumn= 3;
		 }
		 if(name.equals("Mage"))
		 {
		 characterColumn= 4;
		 }
		 if(name.equals("Myrmidon"))
		 {	 
		 characterColumn= 5;
		 }
		 if(name.equals("Pegasus Knight"))
		 {	 
		 characterColumn= 6;
		 }
		 if(name.equals("Thief"))
		 {	 
		 characterColumn= 7;
		 }
		 if(name.equals("Wyvern Rider"))
		 {	 
		 characterColumn= 8;
		 }
		 if(name.equals("Dark Mage"))
		 {
		 characterColumn= 9;	 
		 }
		 	unitType = unitTypes[characterColumn];

			baseHealth = 	combatHealth =		baseStatistics[0][characterColumn]+20;
			baseStrength=	combatStrength =	baseStatistics[1][characterColumn];
			baseMagic=		combatMagic =		baseStatistics[2][characterColumn];
			baseSkill=		combatSkill = 		baseStatistics[3][characterColumn];
			baseSpeed=		combatSpeed = 		baseStatistics[4][characterColumn];
			baseLuck=		combatLuck = 		baseStatistics[5][characterColumn];
			baseDefense=	combatDefense = 	baseStatistics[6][characterColumn];
			baseResistance=	combatResistance = 	baseStatistics[7][characterColumn];
			
			healthGrowth	=	baseHealthGrowth		= 	baseGrowths[0][characterColumn];
			strengthGrowth	=	baseStrengthGrowth		= 	baseGrowths[1][characterColumn];
			magicGrowth		=	baseMagicGrowth			= 	baseGrowths[2][characterColumn];
			skillGrowth		=	baseSkillGrowth			= 	baseGrowths[3][characterColumn];
			speedGrowth		=	baseSpeedGrowth			= 	baseGrowths[4][characterColumn];
			luckGrowth		=	baseLuckGrowth			= 	baseGrowths[5][characterColumn];
			defenseGrowth	=	baseDefenseGrowth		= 	baseGrowths[6][characterColumn];
			resistanceGrowth=	baseResistanceGrowth	= 	baseGrowths[7][characterColumn];
			
			//name of each attack 		
			skillNameAndDescription[0][0] = Skills.allSkills[characterColumn][0];
			skillNameAndDescription[0][1] = Skills.allSkills[characterColumn][1];
			skillNameAndDescription[0][2] = Skills.allSkills[characterColumn][2];
			skillNameAndDescription[0][3] = Skills.allSkills[characterColumn][3];
			
			skillNameAndDescription[1][0] = Skills.allDescriptionsFirstLine[characterColumn][0];
			skillNameAndDescription[1][1] = Skills.allDescriptionsFirstLine[characterColumn][1];
			skillNameAndDescription[1][2] = Skills.allDescriptionsFirstLine[characterColumn][2];
			skillNameAndDescription[1][3] = Skills.allDescriptionsFirstLine[characterColumn][3];
			
			skillNameAndDescription[2][0] = Skills.allDescriptionsSecondLine[characterColumn][0];
			skillNameAndDescription[2][1] = Skills.allDescriptionsSecondLine[characterColumn][1];
			skillNameAndDescription[2][2] = Skills.allDescriptionsSecondLine[characterColumn][2];
			skillNameAndDescription[2][3] = Skills.allDescriptionsSecondLine[characterColumn][3];


	}
	public void equipWeapon(Character user, Weapon unknown)
	{
		if(user.name.equals("Hati"))
		{
			user.weaponName = "Crescent Stone";
			user.weaponClass = "Elemental Stones";
			user.weaponHit = 80;
			user.weaponMight = 13;
			user.weaponCritical = 0;
			user.weaponEffect = "Anti-Beast";
		}
		else if(user.name.equals("Hábrók"))
		{
			user.weaponName = "Aerial Stone";
			user.weaponClass = "Elemental Stones";
			user.weaponHit = 80;
			user.weaponMight = 13;
			user.weaponCritical = 0;
			user.weaponEffect = "Anti-Flyer";
		}
		else if(user.name.equals("Nidhogg"))
		{
			user.weaponName = "Dragon Stone";
			user.weaponClass = "Elemental Stones";
			user.weaponHit = 81;
			user.weaponMight = 15;
			user.weaponCritical = 0;
			user.weaponEffect = "Piercing";
		}
		else if(user.name.equals("Surt"))
		{
			user.weaponName = "Molten Blade";
			user.weaponClass = "Elemental Weapon";
			user.weaponHit = 90;
			user.weaponMight = 13;
			user.weaponCritical = 10;
			user.weaponEffect = "";
		}
		else
		{
		user.weaponName = unknown.getChosenWeaponName();
		user.weaponHit = unknown.getChosenWeaponHit();
		user.weaponMight =  unknown.getChosenWeaponMight();
		user.weaponCritical = unknown.getChosenWeaponCritical();	
		user.hitChance = ((combatSkill*3) + (combatLuck/2)) + weaponHit;
		user.critChance = (combatSkill/2) + weaponCritical;
		user.weaponEffect = unknown.getChosenWeaponEffect();
		user.weaponClass = unknown.getChosenWeaponClass();
		}
	}
	
}
