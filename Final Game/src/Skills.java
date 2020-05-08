public class Skills
{
	static String allSkills[][] =	{{"Skill Up", "Double Shot", "Aimed Shot","Steady Hand"} //archer 
									,{"Momentum", "Luna","Charge","Right out the Gate"} //cavalier
									,{"Health Up","Risky Strike","Savage Stance","Wrath"} //fighter
									,{"Reckless Fighter","Shield Ready","Warding Blow","Heavily Armored"} //knight
									,{"Magic Up","Overcharged Spells","Perfect Storm","Future Sight"} //mage
									,{"Lifetaker","Astra","Evasive Stance","Vantage"} //myrmidon
									,{"Speed Up","Darting Blow","Dive Bomb","Relief"} //pegasus knight
									,{"Luck Up","Cheap Shot","Lethality","Lucky Seven"} //Theif
									,{"Quick Burn","Dive Bomb","Fearsome Blow","Sword Breaker"} //wyvern rider
									,{"Hex","Nosferatu","Vengeance","Tome Breaker"}}; // dark mage

	
	static String[][] allDescriptionsFirstLine =	{{"+2 Skill"
										 ,"Attack for 1.5X damage but Hit Chance -20"
										 ,"+15 Hit Chance but -15 Avoid Chance"
										 ,"+10 Hit Chance and +10 Crit Chance"}
										
										,{"+1 damage and +5 Hit Chance each round "
										 ,"-20 Hit Chance but half enemy defense"
										 ,"+10 Hit Chance and +3 Damage but -10 "
										 ,"+10 SPD for first 3 rounds"}
										
										,{"+5 Max Health"
										 ,"-15% Hit Chance but +10% Crit Chance and "
										 ,"+10% Crit Chance but -5% Dodge Chance"
										 ,"+20% Crit Chance when at or below 50% Max HP"}
										
										,{"+2 Speed but -2 Defense if opponent is faster"
										 ,"-10 Damage received and dealt for the round"
										 ,"-10 Enemy Hit Chance and decrease your "
										 ,"Receive half damage from Axes, Swords, and "}
										
										,{"+2 Magic "
										 ,"+7 Damage and +15 Crit Chance"
										 ,"-20 Hit Chance but half Enemy Resistence "
										 ,"+15 Dodge Chance"}
										
										,{"Critical Hits heal for 15% Max HP"
										 ,"Attack 5 consecutive times for half damage."
										 ,"+30 Avoid Chance but -20 Hit Chance"
										 ,"When below 50% Health, Speed increases by 2"}
										
										,{"+2 Speed"
										 ,"Deal -3 Damage but gain +15 Avoid Chance"
										 ,"Deal +5 Damage but receive -15 Avoid Chance"
										 ,"Regain 10% of Max Health if below 70% Health"}
										
										,{"+2 Luck "
										 ,"-3 Damage dealt but gain +20 Avoid Chance"
										 ,"+20 Crit Chance but take 5 more damage,"
										 ,"+10 to Hit and Avoid for first 4 rounds"}
										
										,{"+15 to Hit and Avoid Chance on odd rounds"
										 ,"Deal +5 damage but -15 to Avoid Chance"
										 ,"+3 Damage and +15 Hit Chance but receive "
										 ,"Avoid +50 and receive 3 less damage if "}
										
										,{"Opponents with Swords/Axes/Lances suffer"
										 ,"-20 to Hit but heal for half the damage dealt"
										 ,"-10 to Hit but deal bonus damage equal to "
										 ,"Hit and Avoid +50 if opponent uses a tome"}};

	static String[][] allDescriptionsSecondLine =	{{"","","",""}
													,{"","","Avoid Chance",""}
													,{"","+5 Damage","",""}
													,{"","","damage by 3 ","Lances. Chance to activate = 40%"}
													,{"","","",""}
													,{"","","",""}
													,{"","","",""}
													,{"","","(Skill/4)% chance to instantly defeat enemy",""}
													,{"","","+2 Damage to you.","opponent is wielding a sword"}
													,{" -15 Avoid","","quarter of missing HP",""}};
	
	public static void healthDmg(Character attacker, Character receiver)
	{
		attacker.damage = 0;
		attacker.hitWorked = false;
		attacker.critWorked = false;		
		double chance = Math.random()*100;
		
		weaponEffect(attacker, receiver);
		
		if(chance <= (attacker.hitChance- receiver.avoidChance))
		{
			attacker.hitWorked = true;
			if(attacker.combatStrength >= attacker.combatMagic)
				receiver.combatHealth -= attacker.damage = (attacker.combatStrength + attacker.weaponMight - receiver.combatDefense + attacker.damageAdder) *attacker.damageMultiplier;
			else if(attacker.combatStrength < attacker.combatMagic)
				receiver.combatHealth -= attacker.damage = (attacker.combatMagic + attacker.weaponMight - receiver.combatResistance + attacker.damageAdder) *attacker.damageMultiplier;
			
			if(chance <= (attacker.critChance - receiver.dodgeChance))
			{
				int lastMultiplier = 2;
				if( attacker.weaponEffect == "Critical")
				{
					lastMultiplier = 3;
				}	
				attacker.critWorked = true;
				if(attacker.combatStrength >= attacker.combatMagic)
					receiver.combatHealth -= attacker.damage = ((attacker.combatStrength + attacker.weaponMight - receiver.combatDefense + attacker.damageAdder) *attacker.damageMultiplier)*lastMultiplier;
				else if(attacker.combatStrength < attacker.combatMagic)
					receiver.combatHealth -= attacker.damage =((attacker.combatMagic + attacker.weaponMight - receiver.combatResistance + attacker.damageAdder) *attacker.damageMultiplier)*lastMultiplier;	
			}	
		}
			
		if (attacker.damage < 0)				// negative damage is not allowed
		{
			receiver.combatHealth += attacker.damage;	
			attacker.damage = 0;
		}
		if (receiver.combatHealth < 0)				// negative health is not allowed
			receiver.combatHealth = 0;
		receiver.healthPercentage = (int) ((receiver.combatHealth*100)/ receiver.baseHealth);
		
		if(attacker.name.equals("Myrmidon") && attacker.critWorked==true)
		{
			attacker.combatHealth += attacker.baseHealth*.15;
		}
		if(attacker.name.equals("Dark Mage") && attacker.healthRegain == true && attacker.hitWorked==true)
		{
			attacker.combatHealth += attacker.damage/2;
		}
		if(attacker.name.equals("Surt") && attacker.healthRegain == true && attacker.hitWorked==true)
		{
			attacker.combatHealth += attacker.damage/2;
		}
	}			
	
	private static void weaponEffect(Character attacker,Character receiver)
	{
		if( attacker.weaponEffect == "Piercing")
		{
			if(receiver.unitType == "A" || receiver.unitType == "BA")
				attacker.damageAdder += attacker.weaponMight;
		}
		if( attacker.weaponEffect == "Anti-Beast")
		{
			if(receiver.unitType == "M" || receiver.unitType == "FM" || receiver.unitType == "B"||receiver.unitType == "BA" || receiver.unitType.equals("BF"))
				attacker.damageAdder += attacker.weaponMight;
		}
		if( attacker.weaponEffect == "Anti-Flyer" || attacker.name == "archer" || attacker.name == "thief")
		{
			if(receiver.unitType == "F" || (receiver.unitType == "FM"))
				attacker.damageAdder += attacker.weaponMight;
		}
		if( attacker.weaponEffect == "Superior")
		{
			if(receiver.weaponEffect == "Superior")
			{
				attacker.hitChance +=50;
				attacker.avoidChance +=50;
			}		
		}
		if( attacker.weaponEffect == "Brave")
		{
			attacker.damageMultiplier *= 2;
		}	
	}
	public static void useSkill(Character attacker, Character receiver, int skillNum)
	{
		if(skillNum == 0||attacker.name.equals("Surt")||attacker.name.equals("Hati")||attacker.name.equals("Hábrók")||attacker.name.equals("Nidhogg"))
		{
			healthDmg(attacker,receiver);
		}
		if(attacker.name.equals("Archer"))
		{
			if(skillNum == 1)
			{
				archerMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				archerMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Fighter"))
		{
			if(skillNum == 1)
			{
				fighterMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				fighterMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Cavalier"))
		{
			if(skillNum == 1)
			{
				cavalierMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				cavalierMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Knight"))
		{
			if(skillNum == 1)
			{
				knightMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				knightMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Mage"))
		{
			if(skillNum == 1)
			{
				mageMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				mageMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Myrmidon"))
		{
			if(skillNum == 1)
			{
				myrmidonMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				myrmidonMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Pegasus Knight"))
		{
			if(skillNum == 1)
			{
				pegasusKnightMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				pegasusKnightMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Thief"))
		{
			if(skillNum == 1)
			{
				thiefMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				thiefMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Wyvern Rider"))
		{
			if(skillNum == 1)
			{
				wyvernRiderMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				wyvernRiderMoveThree(attacker,receiver);
			}
		}
		if(attacker.name.equals("Dark Mage"))
		{
			if(skillNum == 1)
			{
				darkMageMoveTwo(attacker, receiver);
			}
			if(skillNum == 2)
			{
				darkMageMoveThree(attacker,receiver);
			}
		}
	}
	

	// ----------- Archer Skills -------------
	
	private static void archerMoveOne(Character attacker, Character receiver)
	{
		// Skill Up (passive)
		attacker.combatSkill+=2;		
	}
	public static void archerMoveTwo(Character attacker, Character receiver)
	{
		//Double Shot 
		attacker.damageMultiplier *= 1.5;
		attacker.hitChance -= 20;
		healthDmg(attacker,receiver);
		
	}
	private static void archerMoveThree(Character attacker, Character receiver)
	{
		// Aimed Shot
		//+15% Hit but -15% Avoid
		attacker.hitChance = attacker.hitChance+15;
		attacker.avoidChance = attacker.avoidChance-15;
		healthDmg(attacker, receiver);
		
	}
	private static void archerMoveFour(Character attacker, Character receiver)
	{
		// Steady aim (passive)
		attacker.hitChance+=10;
		attacker.critChance+=10;
		
	}
	
	// ----------- Fighter Skills -------------
	
	private static void fighterMoveOne(Character attacker, Character receiver)
	{
		// Health Up (passive)
		attacker.combatHealth+=5;
	}
	public static void fighterMoveTwo(Character attacker, Character receiver)
	{
		// Risky Strike
		// -15% hit Chance but +10% Crit Chance and +5 damage
		attacker.hitChance = attacker.hitChance-15;
		attacker.critChance = attacker.critChance+10;
		attacker.damageAdder +=5;
		healthDmg(attacker, receiver);
		
	}
	private static void fighterMoveThree(Character attacker, Character receiver)
	{
		// Savage stance
		attacker.critChance+=10;
		attacker.dodgeChance-=5;
		healthDmg(attacker,receiver);
	}
	private static void fighterMoveFour(Character attacker, Character receiver)
	{
		//Wrath (passive)
		if(attacker.combatHealth<=attacker.baseHealth/2)
		{
			attacker.critChance+=20;
		}
	}
	// ----------- Cavalier Skills -------------
	
	private static void cavalierMoveOne(Character attacker, Character receiver)
	{
		//Momentum(passive)
		attacker.damageAdder += Driver.round - 1;
		attacker.hitChance += 5*(Driver.round-1);
	}
	public static void cavalierMoveTwo(Character attacker, Character receiver)
	{
		// Luna
		// Attack with a -20% Hit but ignore half of the enemy’s DEF
		attacker.hitChance -=20;
		receiver.combatDefense = receiver.combatDefense/2; 
		healthDmg(attacker,receiver);

	}
	private static void cavalierMoveThree(Character attacker, Character receiver)
	{
		// Charge
		// Attack with a +10% Hit and +5 Damage, but suffer a -10% Avoid
		attacker.hitChance +=10;
		attacker.avoidChance -=10;
		attacker.damageAdder += 3;
		healthDmg(attacker, receiver);
	}
	private static void cavalierMoveFour(Character attacker, Character receiver)
	{
		// Right out the gate (passive)
		attacker.combatSpeed += 10;

	}
	// ----------- Knight Skills -------------
	
	private static void knightMoveOne(Character attacker, Character receiver)
	{
		// Reckless fighter (passive)
		if( attacker.combatSpeed<receiver.combatSpeed)
		{
			attacker.combatDefense = attacker.combatDefense -2;
			attacker.combatSpeed = attacker.combatSpeed +2;
		}
	}
	public static void knightMoveTwo(Character attacker, Character receiver)
	{
		// Shield Up
		attacker.damageAdder -= 10;
		receiver.damageAdder -= 10;
		healthDmg(attacker, receiver);

	}
	private static void knightMoveThree(Character attacker, Character receiver)
	{
		//Warding Blow
		receiver.hitChance-=10;
		receiver.damageAdder -= 3;
		healthDmg(attacker,receiver);
	}
	private static void knightMoveFour(Character attacker, Character receiver)
	{

		// Heavily Armored (passive)
		// Receive half damage from axes, swords, & lances. Chance to activate = (SKL)%
		double chance = Math.random()*100;
		if( chance <= 40 )
		{
			if(receiver.weaponClass.equals("Axe") ||receiver.weaponClass.equals("Lance")||receiver.weaponClass.equals("Sword"))
				receiver.damageMultiplier = .5;
		}

	}
	// ----------- Mage Skills -------------
	
	private static void mageMoveOne(Character attacker, Character receiver)
	{
		//Magic Up(passive)
		attacker.combatMagic+=2;
	}
	public static void mageMoveTwo(Character attacker, Character receiver)
	{
		// Overcharged Spells
		// 7 more damage and crit 
		attacker.damageAdder +=7;
		attacker.critChance += 15;
		healthDmg(attacker,receiver);

	}
	private static void mageMoveThree(Character attacker, Character receiver)
	{		
		// Perfect Storm 
		// -20 hit but half enemy resistance
		attacker.hitChance -= 20;
		receiver.combatResistance = receiver.combatResistance/2;
		healthDmg(attacker,receiver);
	}
	private static void mageMoveFour(Character attacker, Character receiver)
	{
		// Future sight(passive)
		attacker.dodgeChance += 15;

	}
	// ----------- Myrmidon Skills -------------
	
	private static void myrmidonMoveOne(Character attacker, Character receiver)
	{
		// Lifetaker ( passive)
		// critical hits heal 15% max hp
	}
	public static void myrmidonMoveTwo(Character attacker, Character receiver)
	{
		// Astra ( active)
		// attack 5 times for half damage
		attacker.damageMultiplier = 2.5;
		healthDmg(attacker,receiver);

	}
	private static void myrmidonMoveThree(Character attacker, Character receiver)
	{
		//Evasive stance
		attacker.avoidChance += 30;
		attacker.hitChance -= 20;
		healthDmg(attacker,receiver);

	}
	private static void myrmidonMoveFour(Character attacker, Character receiver)
	{
		// Vantage ( passive)
		//when below 50% hp speed increases by 2 each round
		if( attacker.combatHealth < attacker.baseHealth/2)
		{
			attacker.combatSpeed += 2;
		}
	}
	// ----------- Pegasus Knight Skills -------------
	
	private static void pegasusKnightMoveOne(Character attacker, Character receiver)
	{
		//Speed Up(passive)
		attacker.combatSpeed += 2;
	}
	public static void pegasusKnightMoveTwo(Character attacker, Character receiver)
	{
		// Darting Blow
		// -3 damage but gain 15 avoid
		attacker.damageAdder -= 3;
		attacker.avoidChance += 15;
		healthDmg(attacker, receiver);
	}
	private static void pegasusKnightMoveThree(Character attacker, Character receiver)
	{
		// Dive Bomb
		// 5 damage but -15 avoid
		attacker.damageAdder +=5;
		attacker.avoidChance -=15;
		healthDmg(attacker,receiver);
	}
	private static void pegasusKnightMoveFour(Character attacker, Character receiver)
	{
		// Releif (passive)
		// regain 10% max hp if below 70% hp
		if( attacker.combatHealth < attacker.baseHealth*.7)
			attacker.combatHealth += attacker.baseHealth*.1;
	}
	// ----------- Thief Skills -------------
	
	private static void thiefMoveOne(Character attacker, Character receiver)
	{
		//Luck Up (passive)
		attacker.combatLuck+=2;
	}
	public static void thiefMoveTwo(Character attacker, Character receiver)
	{
		// Cheap shot
		// -3 damage but gain +20 avoid
		attacker.damageAdder -= 3;
		attacker.avoidChance +=20;
		healthDmg(attacker,receiver);
	}
	private static void thiefMoveThree(Character attacker, Character receiver)
	{
		// lethality
		attacker.critChance += 20;
		receiver.damageAdder += 5;
		double chance = Math.random()*100;
		if ( chance <= attacker.combatSkill/4)
		{
			receiver.combatHealth = 0;
		}
		healthDmg(attacker,receiver);
	}
	private static void thiefMoveFour(Character attacker, Character receiver)
	{
		// Lucky Seven ( passive)
		// 10 to hit and avoid for first 4 rounds
		if( Driver.round <  4)
		{
			attacker.hitChance += 10;
			attacker.avoidChance-= 10;
		}

	}
	// ----------- Wyvern Rider Skills -------------
	
	private static void wyvernRiderMoveOne(Character attacker, Character receiver)
	{
		// Quick burn ( passive)
		// + 15 hit and avoid on odd rounds
		if ( (double)(Driver.round + 1)%2 ==0)
		{
			attacker.hitChance += 15;
			attacker.avoidChance += 15;
		}

	}
	public static void wyvernRiderMoveTwo(Character attacker, Character receiver)
	{
		// Dive Bomb 
		// 5 to damage but -15 avoid 
		attacker.damageAdder +=5;
		attacker.avoidChance -= 15;
		healthDmg(attacker, receiver);
	}
	private static void wyvernRiderMoveThree(Character attacker, Character receiver)
	{
		//Fearsome Blow
		attacker.damageAdder += 3;
		attacker.hitChance += 15;
		receiver.damageAdder += 2;
		healthDmg(attacker,receiver);

	}
	private static void wyvernRiderMoveFour(Character attacker, Character receiver)
	{
		// Sword Breaker (passive)
		//opponent with a sword means your hit and avoid go up 50
		if ( receiver.weaponClass.equals("Sword"))
		{
			attacker.avoidChance +=50;
			receiver.damageAdder -=3;
		}
	}
	// ----------- Dark Mage Skills -------------
	
	private static void darkMageMoveOne(Character attacker, Character receiver)
	{
		//Hex(passive)
		if ( receiver.weaponClass.equals("Sword")||receiver.weaponClass.equals("Axe")||receiver.weaponClass.equals("Lance"))
		{
			receiver.avoidChance -= 15;
		}
	}
	public static void darkMageMoveTwo(Character attacker, Character receiver)
	{
		// Nosferatu
		// -20 to hit but heal half damage
		attacker.hitChance -= 20;
		attacker.healthRegain = true;
		healthDmg(attacker, receiver);
	}
	private static void darkMageMoveThree(Character attacker, Character receiver)
	{
		// Vengeance
		//hit chance -10 but bonus damage equal to quarter missing hp
		attacker.damageAdder += (attacker.baseHealth - attacker.combatHealth)/4;
		attacker.hitChance -=10;
		healthDmg(attacker,receiver);
	}
	private static void darkMageMoveFour(Character attacker, Character receiver)
	{
		// Tome Breaker ( passive)
		if( receiver.weaponClass.equals("Tome"))
		{
			attacker.hitChance+=50;
			attacker.avoidChance +=50;
		}
	}

	// ----------- Hati Skills -------------
		private static void hatiMoveOne(Character attacker, Character receiver)
		{
			//odd shaped(passive)
			if ((double)Driver.round%2 != 0)
			{
				attacker.damageAdder += 4;
			}
		}
		public static void hatiMoveTwo(Character attacker, Character receiver)
		{
			// Great Hunter
			// attacks have anti beast effect, already coded in
		}
		private static void hatiMoveThree(Character attacker, Character receiver)
		{
			// Regeneration
			//Heal 18 health every odd round
			if((double)Driver.round%2 != 0)
				attacker.combatHealth += 18;
			if(attacker.combatHealth>attacker.baseHealth)
				attacker.combatHealth = attacker.baseHealth;
		}
		private static void hatiMoveFour(Character attacker, Character receiver)
		{
			// Lunacy ( passive)
			// 26 percent chance to activate luna
			int chance = (int) (Math.random()*100);
			if( chance <= 26)
				cavalierMoveTwo(attacker, receiver);
		}
		// ----------- Hábrók Skills -------------
		private static void hábrókMoveOne(Character attacker, Character receiver)
		{
			//even rhythm(passive)
			if ((double)Driver.round%2 == 0)
			{
				attacker.hitChance += 10;
				attacker.avoidChance += 10;
			}
		}
		public static void hábrókMoveTwo(Character attacker, Character receiver)
		{
			// Flying ace(passive)
			// attacks have anti flyer effect, already coded in
		}
		private static void hábrókMoveThree(Character attacker, Character receiver)
		{
			// Regeneration
			//Heal 15 health every even round
			if((double)Driver.round%2 == 0)
				attacker.combatHealth += 15;
			if(attacker.combatHealth>attacker.baseHealth)
				attacker.combatHealth = attacker.baseHealth;
		}
		private static void hábrókMoveFour(Character attacker, Character receiver)
		{
			// Cyclone ( passive)
			// 16 percent chance to trigger astra
			int chance = (int) (Math.random()*100);
			if( chance <= 16)
				myrmidonMoveTwo(attacker, receiver);
		}
		// ----------- Nidhogg Skills -------------
		private static void nidhoggMoveOne(Character attacker, Character receiver)
		{
			//Odd scales(passive)
			if ((double)Driver.round%2 == 0)
			{
				receiver.damageAdder -= 4;;
			}
		}
		public static void nidhoggMoveTwo(Character attacker, Character receiver)
		{
			// Flying ace(passive)
			// attacks have piercing effect, already coded in
		}
		private static void nidhoggMoveThree(Character attacker, Character receiver)
		{
			// Regeneration
			//Heal 15 health every odd round
			if((double)Driver.round%2 != 0)
				attacker.combatHealth += 15;
			if(attacker.combatHealth>attacker.baseHealth)
				attacker.combatHealth = attacker.baseHealth;
		}
		private static void nidhoggMoveFour(Character attacker, Character receiver)
		{
			// Dragon Scales ( passive)
			// 18 percent chance to trigger astra
			int chance = (int) (Math.random()*100);
			if( chance <= 18)
				knightMoveFour(attacker, receiver);
		}
		// ----------- Surt Skills -------------
		
		private static void surtMoveOne(Character attacker, Character receiver)
		{
			//Slow Burn(passive)
			attacker.hitChance ++;
			attacker.avoidChance++;
		}
		public static void surtMoveTwo(Character attacker, Character receiver)
		{
			// Vampiric blade(passive)
			//-10 hit but heal for half damage
			attacker.hitChance -=10;
			attacker.healthRegain = true;
		}
		private static void surtMoveThree(Character attacker, Character receiver)
		{
			// Vengeance+
			//hit chance -10 but bonus damage equal to quarter missing hp
			attacker.damageAdder += (attacker.baseHealth - attacker.combatHealth)/2;
			attacker.hitChance -=10;
		}
		private static void surtMoveFour(Character attacker, Character receiver)
		{
			// Ignis ( passive)
			// 18 percent chance to trigger astra
			int chance = (int) (Math.random()*100);
			if( chance <= 21 && receiver.combatDefense< receiver.combatResistance)
				attacker.damageAdder += attacker.combatMagic/2;
			if( chance <= 21 && receiver.combatDefense> receiver.combatResistance)
				attacker.damageAdder += attacker.combatStrength/2;
		}

	public static void setSelfRoundSkills(Character attacker, Character receiver)
	{
		if(attacker.name.equals("Fighter"))  //before your attack
		{
			fighterMoveFour(attacker,receiver);
		}
		if(attacker.name.equals("Cavalier")) //before your attack
		{		
			cavalierMoveOne(attacker,receiver);	
		}
		if(attacker.name.equals("Myrmidon"))
		{ 
			myrmidonMoveFour(attacker,receiver); // before your attack
		}
		if(attacker.name.equals("Pegasus Knight"))
		{
			pegasusKnightMoveFour(attacker,receiver); // before your attack
		}
		if(attacker.name.equals("Wyvern Rider"))
		{			
			wyvernRiderMoveOne(attacker,receiver); //before your attack
		}
	}
	
	public static void setOtherRoundSkills(Character attacker, Character receiver)
	{
		if(attacker.name.equals("Knight")) // before enemy attacks
		{
			knightMoveFour(attacker,receiver);	
		}
		if(attacker.name.equals("Thief")) 
		{
			thiefMoveFour(attacker,receiver);	// before enemy attack
		}
	}
	
	/**
	 * set the passive skills on when the match starts
	 * @param attacker
	 * @param receiver
	 */
	public static void setMatchSkills(Character attacker, Character receiver)
	{
		if(attacker.name.equals("Archer"))
		{
				archerMoveOne(attacker,receiver);				
				archerMoveFour(attacker,receiver);
		}
		if(attacker.name.equals("Fighter"))
		{
				fighterMoveOne(attacker,receiver);				
		}
		if(attacker.name.equals("Cavalier"))
		{		
				cavalierMoveFour(attacker,receiver);
		}
		if(attacker.name.equals("Knight"))
		{
				knightMoveOne(attacker,receiver);				
		}
		if(attacker.name.equals("Mage"))
		{
				mageMoveOne(attacker,receiver);				
				mageMoveFour(attacker,receiver);
		}
		if(attacker.name.equals("Pegasus Knight"))
		{
				pegasusKnightMoveOne(attacker,receiver);				
		}
		if(attacker.name.equals("Thief"))
		{
				thiefMoveOne(attacker,receiver);				
		}
		if(attacker.name.equals("Wyvern Rider"))
		{			
				wyvernRiderMoveFour(attacker,receiver);
		}
		if(attacker.name.equals("Dark Mage"))
		{
				darkMageMoveOne(attacker,receiver);				
				darkMageMoveFour(attacker,receiver);
		}
	}
	
}
