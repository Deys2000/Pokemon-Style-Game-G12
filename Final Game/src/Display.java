import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Display
{
	 static int yplace = 200; 
	
	 static Font description = new Font("arial", Font.BOLD, 12);
	 static Font nameStyle = new Font("arial", Font.BOLD, 20);
	 static Font statistics = new Font("arial", Font.BOLD, 15);
	 static Font large = new Font("arial", Font.BOLD, 30);
	 
	 
	 
	 public static void playerUpdate(Graphics g, Character player)
	 {	
		 int xplace = 500;
	//the character
		g.setColor(Color.blue);
		g.fillOval(yplace, xplace, 100, 100);
	//the health bar 
		g.setColor(Color.black);
		g.fillRect(yplace + 150, xplace, 100 ,10);
		g.setColor(Color.red);
		g.fillRect(yplace + 150, xplace, player.healthPercentage , 10 );
	//the health number
		g.setFont(nameStyle);
		g.clearRect(yplace+150, xplace + 10, 40, 20);		//erases previous health number
		g.drawString(Integer.toString((int)player.combatHealth), yplace+150, xplace + 30);
	//the character name
		g.setColor(Color.black);
		g.setFont(nameStyle);
	 	g.drawString(player.name, yplace+150, xplace - 10);	
	//the characters attribute names
	 	g.setFont(statistics);
	 	g.drawString("STR|MAG : ", yplace +150 , xplace + 55);
	 	g.drawString("DEF|RES : ", yplace + 150, xplace + 70);
	 	g.drawString("HIT % : ", yplace +150 , xplace + 85);
	 	g.drawString("CRIT % : ", yplace+ 150 , xplace + 100);
	 	g.drawString("SPEED: ", yplace +150 , xplace + 115);
	//the characters attribute values
	 	g.drawString(Double.toString(player.combatStrength)+" | "+Double.toString(player.combatMagic), yplace +240, xplace + 55);
	 	g.drawString(Double.toString(player.combatDefense)+" | "+Double.toString(player.combatResistance), yplace +240, xplace + 70);
	 	g.drawString(Double.toString(player.hitChance), yplace +240, xplace + 85);
	 	g.drawString(Double.toString(player.critChance), yplace+240, xplace + 100);
	 	g.drawString(Double.toString(player.combatSpeed), yplace +240, xplace + 115);	 	
		
		//skill boxes
		g.setColor(Color.yellow);
		g.fillRect(yplace + 340, xplace - 50, 600, 110);
		g.setColor(Color.green);
		g.fillRect(yplace + 340, xplace + 50, 600, 130);
		//skill passive or active
		g.setFont(large);;
		g.setColor(Color.yellow);
		g.drawString("PASSIVE SKILLS", yplace +500, xplace - 55); 
		g.setColor(Color.green);
		g.drawString("ACTIVE SKILLS", yplace +500, xplace + 205);
		
			//skill names
		g.setColor(Color.black);
		g.setFont(nameStyle);
		g.drawString(player.skillNameAndDescription[0][0], yplace +350, xplace - 30);
		g.drawString(player.skillNameAndDescription[0][1], yplace +350, xplace + 70);
		g.drawString(player.skillNameAndDescription[0][2], yplace +650, xplace + 70);
		g.drawString(player.skillNameAndDescription[0][3], yplace +650, xplace - 30);
			//skill effects first line
		g.setFont(description);
		g.drawString(player.skillNameAndDescription[1][0], yplace +350 , xplace - 10);
		g.drawString(player.skillNameAndDescription[1][1], yplace +350 , xplace + 90);
		g.drawString(player.skillNameAndDescription[1][2], yplace +650 , xplace + 90);
		g.drawString(player.skillNameAndDescription[1][3], yplace +650 , xplace - 10);
			//skill effects second line
		g.drawString(player.skillNameAndDescription[2][0], yplace +350 , xplace +5 );
		g.drawString(player.skillNameAndDescription[2][1], yplace +350 , xplace + 100);
		g.drawString(player.skillNameAndDescription[2][2], yplace +650 , xplace + 100);
		g.drawString(player.skillNameAndDescription[2][3], yplace +650 , xplace +5);	 	
						
		g.drawString(player.weaponName, yplace - 180, xplace -10);
		g.drawString("Weapon Might", yplace - 180, xplace + 30);
		g.drawString("Weapon Hit %", yplace - 180, xplace + 70);
		g.drawString("Wepaon Crit %", yplace - 180, xplace + 110);
		
		g.drawString(Double.toString(player.weaponMight), yplace - 80, xplace + 30);
		g.drawString(Double.toString(player.weaponHit), yplace - 80, xplace + 70);
		g.drawString(Double.toString(player.weaponCritical), yplace - 80, xplace + 110);
	 	}
	 
	 public static void opponentUpdate (Graphics g, Character opponent)
	 {
		int xplace = 150;
		//the character
		g.setColor(Color.green);
		g.fillOval(yplace, xplace, 100, 100);
		//the health bar
		g.setColor(Color.black);
		g.fillRect(yplace + 150, xplace, 100 ,10);
		g.setColor(Color.red);
		g.fillRect(yplace + 150, xplace,opponent.healthPercentage, 10 );
		//the health number
		g.setFont(nameStyle);
		g.clearRect(yplace+150, xplace + 10, 40, 20); 
		g.drawString(Integer.toString((int)opponent.combatHealth), yplace+150, xplace + 30);
		//the character name
		g.setColor(Color.black);
		g.setFont(nameStyle);
		g.drawString(opponent.name, yplace+150, xplace - 10);
		//the characters attributes
		g.setFont(statistics);
		g.drawString("STR | MAG : ", yplace +150 , xplace + 55);	 
		g.drawString("DEF | RES : ", yplace + 150, xplace + 70);
		g.drawString("HIT % : ", yplace +150 , xplace + 85);
		g.drawString("CRIT % : ", yplace+ 150 , xplace + 100);
		g.drawString("SPEED : ", yplace+ 150 , xplace + 115);
		//the character attribute values
		g.drawString(Double.toString(opponent.combatStrength)+" | "+Double.toString(opponent.combatMagic), yplace +240, xplace + 55);
	 	g.drawString(Double.toString(opponent.combatDefense)+" | "+Double.toString(opponent.combatResistance), yplace +240, xplace + 70);
	 	g.drawString(Double.toString(opponent.hitChance), yplace +240, xplace + 85);
	 	g.drawString(Double.toString(opponent.critChance), yplace+240, xplace + 100);
	 	g.drawString(Double.toString(opponent.combatSpeed), yplace +240, xplace + 115);
	 	
		//skill boxes
		g.setColor(Color.yellow);
		g.fillRect(yplace + 340, xplace - 50, 600, 110);
		g.setColor(Color.green);
		g.fillRect(yplace + 340, xplace + 50, 600, 130);
		
		//skill names
		g.setColor(Color.black);
		g.setFont(nameStyle);
		g.drawString(opponent.skillNameAndDescription[0][0], yplace +350, xplace - 30); // top left
		g.drawString(opponent.skillNameAndDescription[0][1], yplace +350, xplace + 70); // bottom left
		g.drawString(opponent.skillNameAndDescription[0][2], yplace +650, xplace + 70); // bottom right
		g.drawString(opponent.skillNameAndDescription[0][3], yplace +650, xplace - 30); // top right
		//skill effects
		g.setFont(description);
		g.drawString(opponent.skillNameAndDescription[1][0], yplace +350, xplace - 10);
		g.drawString(opponent.skillNameAndDescription[1][1], yplace +350, xplace + 90);
		g.drawString(opponent.skillNameAndDescription[1][2], yplace +650, xplace + 90);
		g.drawString(opponent.skillNameAndDescription[1][3], yplace +650, xplace - 10);
		
		g.drawString(opponent.skillNameAndDescription[2][0], yplace +350 , xplace +5 );
		g.drawString(opponent.skillNameAndDescription[2][1], yplace +350 , xplace + 100);
		g.drawString(opponent.skillNameAndDescription[2][2], yplace +650 , xplace + 100);
		g.drawString(opponent.skillNameAndDescription[2][3], yplace +650 , xplace +5);	 	
						
		g.drawString(opponent.weaponName, yplace - 180, xplace -10);
		g.drawString("Weapon Might", yplace - 180, xplace + 30);
		g.drawString("Weapon Hit %", yplace - 180, xplace + 70);
		g.drawString("Weapon Crit %", yplace - 180, xplace + 110);
		
		g.drawString(Double.toString(opponent.weaponMight), yplace - 80, xplace + 30);
		g.drawString(Double.toString(opponent.weaponHit), yplace - 80, xplace + 70);
		g.drawString(Double.toString(opponent.weaponCritical), yplace - 80, xplace + 110);
		
	}
	 
	public static void allCharDisplay(Graphics g)
	 {
		 int xPos = 50;
		 int yPos = 100;
		 String allChars[] = {"Archer","Cavalier","Fighter","Knight","Mage","Myrmidon",
				 		 "Pegasus Knight","Thief","Wyvern Rider","Dark Mage"};
	
		 String allStats[][] =	{{"Health", 	"17","17","19","18","15","16","18","16","19","16"}
		 						,{"Strength", 	"05","06","07","08","00","04","06","03","07","00"}
		 						,{"Magic", 		"00","00","00","00","04","01","01","00","00","06"}
		 						,{"Skill", 		"07","05","06","04","05","09","07","06","06","03"}
		 						,{"Speed", 		"05","05","06","02","06","10","10","08","05","03"}
		 						,{"Luck", 		"02","03","02","00","01","00","05","00","00","01"}
		 						,{"Defense", 	"04","05","04","11","01","04","04","02","08","03"}
		 						,{"Resistence", "01","03","01","00","03","01","09","00","00","05"}
		 						,{"Weapon used", "Bow","Sword","Axe","Lance","Tome","Sword","Lance","Bow","Axe","Tome"}};
		 String allGrowth[][] = {{"45","45","45","50","35","40","40","35","45","50"}
		 						,{"15","15","25","25","00","20","10","15","30","05"}
		 						,{"00","00","00","00","20","00","00","05","00","15"}
		 						,{"30","15","20","15","20","15","15","25","15","15"}
		 						,{"15","15","15","10","20","25","20","25","15","15"}
		 						,{"05","15","00","00","00","15","20","00","00","00"}
		 						,{"10","10","10","15","05","05","00","05","10","10"}
		 						,{"05","05","05","05","10","05","20","05","05","10"}};		 					
		 
		 for (int i = 0; i < 10; i++)
		 {
		 
		 	if (i == 5)
		 	{
		 		yPos = 400;
		 		xPos = -(i)*225 + 50;
		 	}
		 	
		 
		 g.setColor(Color.orange);
		 g.fillRect(xPos + (i*225) - 10, yPos - 30, 220 ,290); //makes rectangle
		 
		 g.setFont(nameStyle);
		 g.setColor(Color.BLACK);
		 g.drawString(allChars[i], xPos + (i*225), yPos); //writes char names	
		 
		 g.setFont(statistics);
		 g.drawString("Category  | Base | Growth Rate", xPos + (i*225) -10 , yPos + 30);	 
		 g.setColor(Color.black);
		 
		 	for (int j = 0; j < 8; j++)
		 	{
		 		g.drawString(allStats[j][0], xPos + (i*225) - 5, yPos + (j*20) + 60); //writes stat names
		 		g.drawString(allStats[j][i+1], xPos + (i*225) + 90, yPos + (j*20) + 60); //writes base stats
		 		g.drawString(allGrowth[j][i] + " %", xPos + (i*225) + 140, yPos + (j*20) + 60); //writes growth Rate
		 	}
		 	g.drawString(allStats[8][0], xPos + (i*225)-5, yPos + 250);
		 	g.setColor(Color.BLUE);
		 	g.drawString(allStats[8][i+1], xPos + (i*225)+ 100, yPos + 250);
		 
		 }
	 }	
	public static void displayWeapon (Graphics g,String character)
	{
	 //String allWeapons[] = {"Axes","Swords","Lances","Bows","Tomes"};
	 String allStats[] = {"Name","Might","Hit %","Critical %","Rank","Effect"};
	 String toDisplay[][] = null;
	 
	 String allAxes[][] ={	 {"Brave Axe",		"08", "55", "00","A", "Brave"}
	 						,{"Superior Axe",	"15", "60", "00","B", "Superior"}
	 						,{"Silver Axe",		"15", "65", "00","B", "None"}
	 						,{"Killer Axe",		"08", "65", "25","C", "Critical"}
	 						,{"Volant Axe",		"08", "55", "00","C", "Anti-Flyer"}
	 						,{"Iron Axe",		"08", "70", "00","D", "None"}
	 						,{"Hammer",			"10", "60", "00","D", "Piercing"}
	 					//	,{"Hunting Axe",	}
	 						,{"Bronze Axe",		"06", "80", "00","E", "None"}};
	 
	 String allSwords[][] = {{"Brave Sword",	"06", "75", "00","A", "Brave"}
							,{"Superior Edge",	"11", "80", "00","B", "Superior"}
							,{"Silver Sword",	"11", "85", "00","B", "None"}
							,{"Killer Edge",	"06", "85", "25","C", "Critical"}
							,{"ArmorSlayer",	"08", "80", "00","D", "Piercing"}
							,{"Iron Sword",		"06", "90", "00","D", "None"}
						//	,{"Hunting Sword",	"10", "60", "00","D", "Anti-Beast"}
							,{"Bronze Sword",	"04", "99", "00","E", "None"}};
	 
	 String allLances[][] ={ {"Brave Lance",	"07", "65", "00","A", "Brave"}
							,{"Superior Lance",	"13", "70", "00","B", "Superior"}
							,{"Silver Lance",	"13", "75", "00","B", "None"}
							,{"Killer Lance",	"07", "75", "25","C", "Critical"}
							,{"Beast Killer",	"09", "70", "00","D", "Anti-Beast"}
							,{"Iron Lance",		"07", "80", "00","D", "None"}
						//  ,{"Hunting Sword",	"10", "60", "00","D", "Piercing"}
							,{"Bronze Lance",	"05", "90", "00","E", "None"}};
	 
	 String allBows[][] =	{{"Brave Bow",		"08", "65", "00","A", "Brave"}
							,{"Superior Bow",	"13", "70", "00","B", "Superior"}
							,{"Silver Bow",		"13", "75", "00","B", "None"}
							,{"Killer Bow",		"08", "75", "25","C", "Critical"}
							,{"Hunter's Bow",	"06", "85", "00","D", "Anti-Beast"}
							,{"Iron Bow",		"08", "80", "00","D", "None"}
						//  ,{"Hunting Sword",	"10", "60", "00","D", "Piercing"}
							,{"Bronze Bow",		"06", "90", "00","E", "None"}};
	 
	 String allTomes[][] =	{{"Whirlwind",		"06", "75", "00","A", "Brave"}
							,{"Superior Jolt",	"11", "80", "00","B", "Superior"}
							,{"Thunder",		"11", "85", "00","B", "None"}
							,{"Light Strike",	"06", "85", "25","C", "Critical"}
							,{"Gale",			"08", "80", "00","D", "Anti-Flyer"}
							,{"Bolt",			"06", "90", "00","D", "None"}
						    ,{"Arc",			"10", "60", "00","D", "None"}
							,{"Spark",			"04", "99", "00","E", "None"}};
	
	 if(character.equals("Thief")||character.equals("Archer"))
	 {	 
	 toDisplay= allBows;
	 }
	 if(character.equals("Cavalier")||character.equals("Myrmidon"))
	 {	 
	 toDisplay= allSwords;
	 }
	 if(character.equals("Fighter")||character.equals("Wyvern Rider"))
	 {	 
	 toDisplay= allAxes;
	 }
	 if(character.equals("Knight")||character.equals("Pegasus Knight"))
	 {	 
	 toDisplay= allLances;
	 }
	 if(character.equals("Mage")||character.equals("Dark Mage"))
	 {	 
	 toDisplay= allTomes;
	 }
	 
	 int xPos = 100;
	 int yPos = 200;
	 
	 g.setColor(Color.magenta);
	 g.fillRect(xPos -20, yPos -80, 1000, 500);
	 g.setColor(Color.black);
	 g.setFont(large);
	 g.drawString("Weapons Arsenal",100 , 100);
	 g.setFont(nameStyle);
	 
	 int columnExtend = 0;
	 for(int i = 0; i <= toDisplay.length ; i++)
	 {
		 if(i >= 1)
		 {
			 columnExtend = 190;
		 }
		 if(i < 6)
			 g.drawString(allStats[i], xPos + (i*100)+ columnExtend, 180);
		 columnExtend = 0;
		 yPos += 40;
		 for( int j = 0; j < toDisplay[i].length; j++ )
		 {
			 if(j >= 1)
				 {
				 columnExtend = 200;
				 }
			 g.drawString(toDisplay[i][j], xPos + (j*100)+ columnExtend, yPos);
			 g.setColor(Color.gray);
			 g.fillRect(xPos + (j*20)+ columnExtend, yPos+5, 500, 5);
			 g.setColor(Color.black);
			 columnExtend = 0;
		 }
	 }
		
	}

	public static void statsAndSkills(Graphics g ,Character player)
	{
		int xPos = 25;
		int yPos = 75;
		g.setColor(Color.blue);
		g.fillOval(xPos, yPos, xPos + 175, yPos + 125);
		
		g.setFont(large);
		g.setColor(Color.BLACK);
		g.drawString( player.name, xPos + 250, yPos + 20);
		g.drawString( "level: 1", xPos + 250, yPos + 70);
		g.drawString( "Points: "+Driver.poolPoints, xPos + 250, yPos + 120);
		
		g.setFont(nameStyle);
		g.drawString( "You cannot assign more than 80% to one category", xPos + 250, yPos + 150);
		
		g.drawString("Health: "	, 	xPos, yPos + 280);
		g.drawString("Strength: ",	xPos, yPos + 320);
		g.drawString("Magic: ", 	xPos, yPos + 360);
		g.drawString("Skill: ", 	xPos, yPos + 400);
		g.drawString("Speed: ", 	xPos, yPos + 440);
		g.drawString("Luck: ", 		xPos, yPos + 480);
		g.drawString("Defense: ", 	xPos, yPos + 520);
		g.drawString("Resistance: ",xPos, yPos + 560);
		
		g.drawString(Double.toString(player.baseHealth), 	xPos + 120, yPos + 280);
		g.drawString(Double.toString(player.baseStrength),	xPos + 120, yPos + 320);
		g.drawString(Double.toString(player.baseMagic), 	xPos + 120, yPos + 360);
		g.drawString(Double.toString(player.baseSkill), 	xPos + 120, yPos + 400);
		g.drawString(Double.toString(player.baseSpeed), 	xPos + 120, yPos + 440);
		g.drawString(Double.toString(player.baseLuck), 		xPos + 120, yPos + 480);
		g.drawString(Double.toString(player.baseDefense), 	xPos + 120, yPos + 520);
		g.drawString(Double.toString(player.baseResistance),xPos + 120, yPos + 560);
		
		g.drawString("Health: "	, 		xPos + 250, yPos + 280);
		g.drawString("Strength: ",		xPos + 250, yPos + 320);
		g.drawString("Magic: ", 		xPos + 250, yPos + 360);
		g.drawString("Skill: ", 		xPos + 250, yPos + 400);
		g.drawString("Speed: ", 		xPos + 250, yPos + 440);
		g.drawString("Luck: ", 			xPos + 250, yPos + 480);
		g.drawString("Defense: ", 		xPos + 250, yPos + 520);
		g.drawString("Resistance: ",	xPos + 250, yPos + 560);
		
		g.drawString(Double.toString(player.baseHealthGrowth) + "%", 	xPos + 120 +250, yPos + 280);
		g.drawString(Double.toString(player.baseStrengthGrowth) +"%",	xPos + 120 +250, yPos + 320);
		g.drawString(Double.toString(player.baseMagicGrowth) +"%", 		xPos + 120 +250, yPos + 360);
		g.drawString(Double.toString(player.baseSkillGrowth) +"%", 		xPos + 120 +250, yPos + 400);
		g.drawString(Double.toString(player.baseSpeedGrowth) +"%", 		xPos + 120 +250, yPos + 440);
		g.drawString(Double.toString(player.baseLuckGrowth) +"%", 		xPos + 120 +250, yPos + 480);
		g.drawString(Double.toString(player.baseDefenseGrowth) +"%", 	xPos + 120 +250, yPos + 520);
		g.drawString(Double.toString(player.baseResistanceGrowth)+"%",	xPos + 120 +250, yPos + 560);
		
		
	}

	public static void storylineImage(Graphics g)
	{
		for( int i = 0; i < 500; i++)
		{
			int xplacement = (int) (Math.random()*Driver.FRAME_WIDTH);
			int yplacement = (int) (Math.random()*Driver.FRAME_LENGTH);
			int radius	= 	(int) (Math.random()*200);
			int red = (int) (Math.random()*250);
			int blue = (int)(Math.random()*250);
			int green = (int) (Math.random()*250);
			g.setColor(new Color(red,blue,green));
			g.fillOval(xplacement, yplacement, radius, radius);
		}	
		
	}
}
