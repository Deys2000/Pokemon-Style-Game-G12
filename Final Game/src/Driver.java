import javax.swing.*;
import java.awt.*;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Driver extends JFrame
{
	final static int FRAME_LENGTH = 720;
	final static int FRAME_WIDTH = 1200;
	
	static int round = 0;
	static int match = 0;
		
	static String inputUser;
	static String move;
	static String whatToDisplay = "characterselect";
	static String currentWeapon = null;
	static String characterType;
	
	static Character player;
	static Character opponent;
	static Weapon playerWeapon;
	static Weapon opponentWeapon;
	
	static int moveNum = 5;
	
	static String allocationArea = null;
	static int allocationPoints = 0;
	static boolean correctInput = true;
	static int poolPoints = 300;
	static boolean passedEighty = false;
	
	boolean gameStart = true;
	
	static Text plot = new Text();	
	static Driver frame = new Driver();
	
	public static void main (String[] args)
	{
		
		frame.setTitle("Battle Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH,FRAME_LENGTH);
		
		//game starts
		
		JOptionPane.showMessageDialog(frame, "READY? GAME IS STARTING!"
											+ "\n\nPLEASE NOTE: THIS GAME REQUIRES KEYBOARD INPUT"
											+ "\nSO MAKE SURE YOU TYPE PROPERLY"
											+ "\nSPELLING AND CAPITALIZATION ARE KEY");
		//classSelect("start?");
		frame.setVisible(true);	
		whatToDisplay = "textMode";
		frame.repaint();
		stringRecursion(plot.adventureStart, "", plot.adventureStart.length, 0 );
		
		//choosing your class
		whatToDisplay = "characterSelect";
		frame.repaint(); //displays all char and all stats
		classSelect("Select a Character to play this game as\n(Mage,Fighter,Archer)");
		player = new Character(inputUser); //input user holds the class type of opponent
		characterType = inputUser;
				
		// spending the pool of growth rate points
		whatToDisplay = "stats and skills";
		frame.repaint();
		do
		{
		growthPool(player); //chooses the area and the number of points
		frame.repaint();
		if(passedEighty == true)
			JOptionPane.showMessageDialog(null,"Assigned "+ allocationPoints + " points to " + allocationArea + " growth");
		}
		while(poolPoints > 0);
		
		//choosing a weapon
		//weapon type will forever hold the string of the character selected
		whatToDisplay = "weaponSelect";
		frame.repaint();
		weaponSelect("Choose Your Weapon)",Weapon.weaponClass);
		playerWeapon = new Weapon(inputUser);
		player.equipWeapon(player, playerWeapon); //input user holds the name of the weapon
	
		while(true)
		{
			whatToDisplay = "textMode";
			frame.repaint();
			gameText(match);
			
			//choosing opponents class
			//whatToDisplay = "characterSelect";
			//frame.repaint();
			opponentGenerate();
			//classSelect("Enter your opponents class\n(mage,fighter,archer)");
			opponent = new Character(inputUser);
			//frame.repaint();
		
			characterType = inputUser; //character type currently holds the opponent type
			//whatToDisplay = "weaponSelect";
			//frame.repaint();
			opponentWeaponGenerate(Weapon.weaponClass);
			//weaponSelect("Choose Your opponents Weapon\n(according to rank)",Weapon.weaponClass);
			opponentWeapon = new Weapon(inputUser);
			opponent.equipWeapon(opponent, opponentWeapon); //input user holds the name of the weapon
			for(int i = 0; i < match*5; i++)
			{
				levelUp(opponent);
			}
			healPlayer(opponent);
			
			
			Skills.setMatchSkills(player, opponent);
			Skills.setMatchSkills(opponent, player);
			
			whatToDisplay = "combatProcess";
			frame.repaint();
			JOptionPane.showMessageDialog(null,"The fight begin's!!!");
			while(true)
			{
				//deciding who goes first by speed
				
				
				if( player.combatSpeed >= opponent.combatSpeed)
				{
					//players move
					player.damageAdder = 0;
					player.damageMultiplier = 1;
					Skills.setSelfRoundSkills(player,opponent);
					Skills.setOtherRoundSkills(opponent, player);
					playerMove("Will you use a skill?\njust type ''no'' to use a regular attack");
					Skills.useSkill(player, opponent, moveNum);
					
					if(player.critWorked == true)
						JOptionPane.showMessageDialog(null,"Critical Hit!");
					if(player.hitWorked == false)
						JOptionPane.showMessageDialog(null,"Your Attack Missed");
					
					frame.repaint();
					if(player.hitWorked == true)
						JOptionPane.showMessageDialog(null,"Your attack did "+ (int)player.damage +" damage");
					if (opponent.combatHealth <= 0) 
						{
						JOptionPane.showMessageDialog(null,"you won the fight");
						break;
						}
					//opponents move
					opponent.damageAdder = 0;
					opponent.damageMultiplier = 1;
					Skills.setSelfRoundSkills(opponent, player);
					Skills.setOtherRoundSkills(player, opponent);
					opponentMove("what move will the opponent use?");
					Skills.healthDmg( opponent, player); // opponent attacks player
					
					if(opponent.critWorked == true)
						JOptionPane.showMessageDialog(null,"Opponent got a Critical Hit!");
					if(opponent.hitWorked == false)
						JOptionPane.showMessageDialog(null,"You Dodged the "+opponent.name+"! Nice!");
	
					frame.repaint();
					if(opponent.hitWorked == true)
						JOptionPane.showMessageDialog(null,"You received "+ (int)opponent.damage +" damage");
					
					if ( player.combatHealth <= 0)
					{
						JOptionPane.showMessageDialog(null,"you lost the fight, your story ends here");
						frame.setVisible(false);
						break;
					}	
					round++;
					
				}	
				else
				{
					//opponents move
					opponent.damageAdder = 0;
					opponent.damageMultiplier = 1;
					Skills.setSelfRoundSkills(opponent, player);
					Skills.setOtherRoundSkills(player, opponent);
					opponentMove("what move will the opponent use?");
					Skills.healthDmg( opponent, player); // opponent attacks player
					
					if(opponent.critWorked == true)
						JOptionPane.showMessageDialog(null,"Opponent got a Critical Hit!");
					if(opponent.hitWorked == false)
						JOptionPane.showMessageDialog(null,"You dodged the " + opponent.name +"!  Nice!");
					frame.repaint();
					if(opponent.hitWorked == true)
						JOptionPane.showMessageDialog(null,"You received "+ (int)opponent.damage +" damage");
					
					if ( player.combatHealth <= 0)
					{
						JOptionPane.showMessageDialog(null,"you lost the fight, your story ends here");	
						frame.setVisible(false);
						break;
					}
					
					//players move
					player.damageAdder = 0;
					player.damageMultiplier = 1;
					Skills.setSelfRoundSkills(player,opponent);
					Skills.setOtherRoundSkills(opponent, player);
					playerMove("Will you use a skill?\nJust type ''no'' to use a regular attack");
					//Skills.healthDmg( player, opponent); //player attacks opponent 
					Skills.useSkill(player, opponent, moveNum);
					
					if(player.critWorked == true)
						JOptionPane.showMessageDialog(null,"Critical Hit!");
					if(player.hitWorked == false)
						JOptionPane.showMessageDialog(null,"Your Attack Missed");
					frame.repaint();
					if(player.hitWorked == true)
						JOptionPane.showMessageDialog(null,"Your attack did "+ (int)player.damage +" damage");
					
					if (opponent.combatHealth <= 0) 
					{
						JOptionPane.showMessageDialog(null,"you won the fight");
						break;
					}	
					
					round++;
				}	
				
			}	
			
			if(player.combatHealth == 0) // if you lose a fight exit the game
			{
				break;
			}
			
			match++;
			round = 0;
			for(int i = 0; i < 2; i++)
				levelUp(player);
			healPlayer(player);
			
			
			if(match == 13) // after 13 fights,the game ends
			{
				stringRecursion(plot.fifteenTheEnd,"",plot.fifteenTheEnd.length,0);
				break;
			}	
			
		}
		
		frame.setVisible(false);		
	}

	//////////////////////////////////// main driver class ends here, methods from now onwards /////////////////////////////
	private static void gameText( int matchNumber)
	{
		switch(matchNumber) 
		{
			case 0:
				stringRecursion(plot.enteringTheCity,"", plot.enteringTheCity.length, 0);
				stringRecursion(plot.oneTheThief, "", plot.oneTheThief.length, 0);	
				break;
			case 1:
				stringRecursion(plot.twoTheArcher, "", plot.twoTheArcher.length, 0);
				break;
			case 2:
				stringRecursion(plot.threeWeaponSwitch, "", plot.threeWeaponSwitch.length, 0);
				whatToDisplay = "weaponSelect";
				frame.repaint();
				weaponSelect("Choose Your Weapon)",Weapon.weaponClass);
				playerWeapon = new Weapon(inputUser);
				player.equipWeapon(player, playerWeapon);
				whatToDisplay = "textMode";
				frame.repaint();
				stringRecursion(plot.threeTheMage, "", plot.threeTheMage.length, 0);
				break;
			case 3:
				stringRecursion(plot.fourTheCavalier,"",plot.fourTheCavalier.length,0);
				break;
			case 4:
				stringRecursion(plot.fiveTheWeaponSwitch,"",plot.fiveTheWeaponSwitch.length, 0);
				whatToDisplay = "weaponSelect";
				frame.repaint();
				weaponSelect("Choose Your Weapon)",Weapon.weaponClass);
				playerWeapon = new Weapon(inputUser);
				player.equipWeapon(player, playerWeapon);
				whatToDisplay = "textMode";
				frame.repaint();
				stringRecursion(plot.fiveTheKnight,"",plot.fiveTheKnight.length,0);
				break;
			case 5:
				stringRecursion(plot.sixTheDarkMage,"",plot.sixTheDarkMage.length,0);
				break;
			case 6:
				stringRecursion(plot.sevenTheMyrmidon,"",plot.sevenTheMyrmidon.length,0);
				break;
			case 7:
				stringRecursion(plot.eightTheWeaponSwitch,"",plot.eightTheWeaponSwitch.length,0);
				whatToDisplay = "weaponSelect";
				frame.repaint();
				weaponSelect("Choose Your Weapon)",Weapon.weaponClass);
				playerWeapon = new Weapon(inputUser);
				player.equipWeapon(player, playerWeapon);
				whatToDisplay = "textMode";
				frame.repaint();
				stringRecursion(plot.eightTheFighter,"",plot.eightTheFighter.length,0);
				break;
			case 8:
				stringRecursion(plot.nineThePegasusKnight,"",plot.nineThePegasusKnight.length,0);
				break;
			case 9:
				stringRecursion(plot.tenTheWyvernRider,"",plot.tenTheWyvernRider.length,0);
				break;
			case 10:
				stringRecursion(plot.elevenTheKing,"",plot.elevenTheKing.length,0);
				stringRecursion(plot.elevenTheThreeBeasts,"",plot.elevenTheThreeBeasts.length,0);
				whatToDisplay = "weaponSelect";
				frame.repaint();
				weaponSelect("Choose Your Weapon)",Weapon.weaponClass);
				playerWeapon = new Weapon(inputUser);
				player.equipWeapon(player, playerWeapon);
				whatToDisplay = "textMode";
				frame.repaint();
				stringRecursion(plot.elevenTheHati,"",plot.elevenTheHati.length,0);
				break;
			case 11:
				stringRecursion(plot.twelveTheHábrók,"",plot.twelveTheHábrók.length,0);
				break;
			case 12:
				stringRecursion(plot.thirteenTheNidhogg,"",plot.thirteenTheNidhogg.length,0);
				break;
			case 13:
				stringRecursion(plot.fourteenTheSurt,"",plot.fourteenTheSurt.length,0);
				break;
				
			default:
				//
		}
	}
	private static void stringRecursion(String[] exerpt,String stockpile, int loopNum, int baseNum)
	{
		stockpile+= "\n" + exerpt[baseNum];
		JOptionPane.showMessageDialog(null, stockpile);
		
		baseNum++;
		if (baseNum < loopNum)
			stringRecursion(exerpt, stockpile, loopNum, baseNum);
	}
	
	private static void opponentWeaponGenerate(String[][] weaponClass)
	{
		int weild = 0;
		 if(characterType.equals("Thief")||characterType.equals("Archer"))
		 {	 
		 weild= 0;
		 }
		 if(characterType.equals("Cavalier")||characterType.equals("Myrmidon"))
		 {	 
		 weild= 1;
		 }
		 if(characterType.equals("Fighter")||characterType.equals("Wyvern Rider"))
		 {	 
		 weild= 2;
		 }
		 if(characterType.equals("Knight")||characterType.equals("Pegasus Knight"))
		 {	 
		 weild= 3;
		 }
		 if(characterType.equals("Mage")||characterType.equals("Dark Mage"))
		 {	 
		 weild= 4;
		 }
		 
		if(round > 9 )
			inputUser = weaponClass[weild][weaponClass[weild].length-4];
		else if(round > 6)
			inputUser = weaponClass[weild][weaponClass[weild].length-3];
		else if(round > 3)
			inputUser = weaponClass[weild][weaponClass[weild].length-2];
		else
			inputUser = weaponClass[weild][weaponClass[weild].length-1];
		
	}

	private static void opponentGenerate()
	{
		inputUser = "archer"; //default
		if(match == 0)
			inputUser = "Thief";
		if(match == 1)
			inputUser = "Archer";
		if(match == 2)
			inputUser = "Mage";
		if(match == 3)
			inputUser = "Cavalier";
		if(match == 4)
			inputUser = "Knight";
		if(match == 5)
			inputUser = "Dark Mage";
		if(match == 6)
			inputUser = "Myrmidon";
		if(match == 7)
			inputUser = "Fighter";
		if(match == 8)
			inputUser = "Pegasus Knight";
		if(match == 9)
			inputUser = "Wyvern Rider";
		if(match == 10)
			inputUser = "Hati";
		if(match == 11)
			inputUser = "Hábrók";
		if(match == 12)
			inputUser = "Nidhogg";
		if(match == 13)
			inputUser = "Surt";	
	}

	private static void levelUp(Character user)
	{
		double increase;
		
		increase = Math.random()*100;
		if(increase <= user.baseHealthGrowth)
			user.baseHealth++;
		increase = Math.random()*100;
		if(increase <= user.baseStrengthGrowth)
			user.baseStrength++;
		increase = Math.random()*100;
		if(increase <= user.baseMagicGrowth)
			user.baseMagic++;
		increase = Math.random()*100;
		if(increase <= user.baseSkillGrowth)
			user.baseSkill++;
		increase = Math.random()*100;
		if(increase <= user.baseSpeedGrowth)
			user.baseSpeed++;
		increase = Math.random()*100;
		if(increase <= user.baseLuckGrowth)
			user.baseLuck++;
		increase = Math.random()*100;
		if(increase <= user.baseDefenseGrowth)
			user.baseDefense++;
		increase = Math.random()*100;
		if(increase <= user.baseResistanceGrowth)
			user.baseResistance++;	
	}

	private static void healPlayer(Character user)
	{
		//resetting all the health and start changes after every match
		user.combatHealth = 	user.baseHealth;
		user.combatStrength = 	user.baseStrength;
		user.combatMagic = 		user.baseMagic;
		user.combatSkill =		user.baseSkill;
		user.combatSpeed = 		user.baseSpeed;
		user.combatLuck = 		user.baseLuck;
		user.combatDefense = 	user.baseDefense;
		user.combatResistance = user.baseResistance;
		
		user.healthPercentage = (int)(((user.combatHealth*100)/user.baseHealth)+.5);	
		
		user.hitChance = ((user.combatSkill*3) + (user.combatLuck/2)) + user.weaponHit;
		user.critChance = (user.combatSkill/2) + user.weaponCritical;
		user.avoidChance = ((user.combatSpeed * 3)+user.combatLuck)/2;
		user.dodgeChance = user.combatLuck;
		
		user.damageAdder = 0;
		user.damageMultiplier = 1;
	}
	
	public void paint(Graphics g) 
	 {
		 g.setColor(Color.white);
		 g.clearRect(0, 0, Driver.FRAME_WIDTH, Driver.FRAME_LENGTH);
		 
		 if (whatToDisplay.equals("characterSelect"))
		 { 
			 Display.allCharDisplay(g);
		 }
		 if(whatToDisplay.equals("weaponSelect")) 
		 {
			 Display.displayWeapon(g,player.name);
		 }
		 if (whatToDisplay.equals("combatProcess"))
		 { 
			 Display.playerUpdate(g, player);
			 Display.opponentUpdate(g, opponent);	
		 }
		 if(whatToDisplay.equals("stats and skills"))
		 {
			 Display.statsAndSkills(g, player);
		 }
		 if(whatToDisplay.equals("textMode"))
		 {
			 Display.storylineImage(g);
		 }
	 }

	private static void growthPool(Character user)
	{
		// string, int and allaocte points ;)
		
		do 
		{
		allocationArea = JOptionPane.showInputDialog("where will you allocate skill points"
												+ "\n you have " + poolPoints +" left");
		
		if ( allocationArea.equals("Health") || allocationArea.equals("Strength") || allocationArea.equals("Magic")
			  ||allocationArea.equals("Skill")||allocationArea.equals("Speed") || allocationArea.equals("Luck") || allocationArea.equals("Defense")
			  ||allocationArea.equals("Resistance"))
		{
			
			try { 
				allocationPoints = Integer.parseInt(JOptionPane.showInputDialog("how many points will you allocate in this section?"
						+ "\n you have " + poolPoints +" left"));

		        } catch(NumberFormatException e) { 
		          JOptionPane.showMessageDialog(null, "Value must be an integer!");
		        }
			
			if( allocationPoints > poolPoints || allocationPoints > 80)
			{
				JOptionPane.showMessageDialog(null,"invalid input, value too high, type carefully and properly");
				correctInput = false;
			}
			else
			{
				if(allocationArea.equals("Health"))
				{
					user.baseHealthGrowth += allocationPoints;
					passedEighty = false;
					if ( user.healthGrowth+80 < user.baseHealthGrowth)
					{
						user.baseHealthGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Strength"))
				{
					user.baseStrengthGrowth += allocationPoints;
					passedEighty = false;
					if ( user.strengthGrowth+80 < user.baseStrengthGrowth)
					{
						user.baseStrengthGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Magic"))
				{
					user.baseMagicGrowth += allocationPoints;
					passedEighty = false;
					if ( user.magicGrowth+80 < user.baseMagicGrowth)
					{
						user.baseMagicGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Skill"))
				{
					user.baseSkillGrowth += allocationPoints;	
					passedEighty = false;
					if ( user.skillGrowth+80 < user.baseSkillGrowth)
					{
						user.baseSkillGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Speed"))
				{
					user.baseSpeedGrowth += allocationPoints;
					passedEighty = false;
					if ( user.speedGrowth+80 < user.baseSpeedGrowth)
					{
						user.baseSpeedGrowth-= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Luck"))
				{
					user.baseLuckGrowth += allocationPoints;
					passedEighty = false;
					if ( user.luckGrowth+80 < user.baseLuckGrowth)
					{
						user.baseLuckGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Defense"))
				{
					user.baseDefenseGrowth += allocationPoints;
					passedEighty = false;
					if ( user.defenseGrowth+80 < user.baseDefenseGrowth)
					{
						user.baseDefenseGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if(allocationArea.equals("Resistance"))
				{
					user.baseResistanceGrowth += allocationPoints;
					passedEighty = false;
					if ( user.resistanceGrowth+80 < user.baseResistanceGrowth)
					{
						user.baseResistanceGrowth -= allocationPoints;
						passedEighty = true;
					}
				}
				if( passedEighty == true)
					JOptionPane.showMessageDialog(null,"You cannot exceed 80 allocated points for one category.");
				if( passedEighty == false)
				{
				poolPoints -= allocationPoints;
				correctInput = true;
				}
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"invalid input, type carefully and properly");
			correctInput = false;
		}
		}while(correctInput == false);
		
	}

	public static void classSelect(String prompt)
	{
		inputUser = JOptionPane.showInputDialog(prompt);
		if (inputUser.equals("Archer") || inputUser.equals("Cavalier") || inputUser.equals("Fighter")
			  ||inputUser.equals("Knight")||inputUser.equals("Mage") || inputUser.equals("Myrmidon") || inputUser.equals("Pegasus Knight")
			  ||inputUser.equals("Thief")||inputUser.equals("Wyvern Rider") || inputUser.equals("Dark Mage"))
		{
			return;
		}
		else
		{
			classSelect("TYPE PROPERLY\n"+ prompt); //recursion
		}
	}
	 public static void opponentMove(String question)
		{
			//move = JOptionPane.showInputDialog(question);
			JOptionPane.showMessageDialog(null, "opponent is attacking!");
			moveNum = (int) (Math.random()*4);
			/**
			if     (move.equals(opponent.attackName[0][0]))
			{
				moveNum=0;
				return;
			}
			else if(move.equals(opponent.attackName[0][1]))
			{
				moveNum=1;
				return;
			}
			else if(move.equals(opponent.attackName[0][2]))
			{
				moveNum=2;
				return;
			}
			else if(move.equals(opponent.attackName[0][3]))
			{
				moveNum=3;
				return;
			}
			else
			{
				opponentMove("TYPE PROPERLY\n"+ question); //recursion
			}	*/	
		}
	 
	public static void playerMove(String question)
	{
		move = JOptionPane.showInputDialog(question);
		
		if     (move.equals(player.skillNameAndDescription[0][0]))
		{
			playerMove("Passive skills are always operating\nYou cannot choose a passive skill\nChoose an active skill to use\n\n"+ question); //recursion
			//moveNum=0;
			return;
		}
		else if(move.equals(player.skillNameAndDescription[0][1]))
		{
			moveNum=1;
			return;
		}
		else if(move.equals(player.skillNameAndDescription[0][2]))
		{
			moveNum=2;
			return;
		}
		else if(move.equals(player.skillNameAndDescription[0][3]))
		{
			playerMove("Passive skills are always operating\nYou cannot choose a passive skill\nChoose an active skill to use\n\n"+ question); //recursion
			//moveNum=3;
			return;
		}
		else if(move.equals("no"))
		{
			moveNum=0;
			return;
		}
		else
		{
			playerMove("TYPE PROPERLY\n"+ question); //recursion
		}	
	}
	private static void weaponSelect(String prompt, String[][] weaponList)
	{
		 inputUser = JOptionPane.showInputDialog(prompt);  //it works!!!
		 int weild = 0;
		 if(player.name.equals("Thief")||player.name.equals("Archer"))
		 {	 
		 weild= 0;
		 }
		 if(player.name.equals("Cavalier")||player.name.equals("Myrmidon"))
		 {	 
		 weild= 1;
		 }
		 if(player.name.equals("Fighter")||player.name.equals("Wyvern Rider"))
		 {	 
		 weild= 2;
		 }
		 if(player.name.equals("Knight")||player.name.equals("Pegasus Knight"))
		 {	 
		 weild= 3;
		 }
		 if(player.name.equals("Mage")||player.name.equals("Dark Mage"))
		 {	 
		 weild= 4;
		 }
		 boolean mistype = true;
		 	 for(int i = 0; i < weaponList[weild].length; i++)
			 {
		 		 
				 if (inputUser.equals(weaponList[weild][i]))
				 {
					 inputUser = weaponList[weild][i];
					 mistype = false;
				 }
			 }
		 	 
		 	 if( mistype == false )
		 	 {
		 		  JOptionPane.showMessageDialog(null,"You have chosen the " + inputUser );
		 		  return;
		 	 }
		 	 else
		 	 {
				weaponSelect("TYPE PROPERLY\n"+ prompt, weaponList); //recursion
		 	 }

		
	}
}
