package monopoly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Board {
	
	private CardCell[] cards;
	private PropertyCell[] properties;
	private RailroadCell[] stations;
	private MiscCell[] misc;
	private UtilityCell[] utilities;
	
	public Board() {
		cards = new CardCell[6];
		properties = new PropertyCell[22];
		stations = new RailroadCell[4];
		misc = new MiscCell[6];
		utilities = new UtilityCell[2];
		this.initBoard(cards, properties, stations, misc);
		System.out.println("Board init done");
	}
	
	public CardCell[] getCardCells() {
		return this.cards;
	}
	
	public PropertyCell[] getProperties() {
		return this.properties;
	}
	
	public RailroadCell[] getRailroadCells() {
		return this.stations;
	}
	
	public UtilityCell[] getUtilityCells() {
		return this.utilities;
	}
	
	public MiscCell[] getMiscCells() {
		return this.misc;
	}
	
	public String getCellType(int id) {
		
		int index; 
		
		for(index=0; index<this.getProperties().length; index++) {
			
			if(id == this.getProperties()[index].getId()) return this.getProperties()[index].getClass().getName();
		}
		
		for(index=0; index<this.getMiscCells().length; index++) {
			
			if(id == this.getMiscCells()[index].getId()) return this.getMiscCells()[index].getClass().getName();
		}
		
		for(index=0; index<this.getCardCells().length; index++) {
			
			if(id == this.getCardCells()[index].getId()) return this.getCardCells()[index].getClass().getName();
		}
		
		for(index=0; index<this.getRailroadCells().length; index++) {
			
			if(id == this.getRailroadCells()[index].getId()) return this.getRailroadCells()[index].getClass().getName();
		}
		
		for(index=0; index<this.getUtilityCells().length; index++) {
			
			if(id == this.getUtilityCells()[index].getId()) return this.getUtilityCells()[index].getClass().getName();
		}
		
		return "Not found";
	}

	public void initBoard(CardCell cards[], PropertyCell properties[], RailroadCell stations[], MiscCell misc[]) {
		
		File propertiesFile = new File("src\\monopoly\\propertyCards.txt");
		File stationsFile = new File("src\\monopoly\\railwayStations.txt");
		List<String> tokens = new ArrayList<>();
		
		try {
			Scanner propertyInput = new Scanner(propertiesFile);
			Scanner stationsInput = new Scanner(stationsFile);
			
			int stationCount = 0, propertyCount = 0, miscCount = 0, cardCount = 0, utilityCount = 0;
			Integer index = 0;
			
			while(index < 40) {

				if(index.equals(2) || index.equals(18) || index.equals(34) ) {
					cards[cardCount] = new CardCell("Απόφαση", 0, index);
					index++;
					cardCount++;
					//System.out.printf("Cell %d added %s\n", index, "Απόφαση");			
					continue;
				}
				
				if(index.equals(7) || index.equals(22) || index.equals(36) ){
					cards[cardCount] = new CardCell("Εντολή", 1, index);
					cardCount++;
					//System.out.printf("Cell %d added %s\n", index, "Εντολή");
					index++;
					
				}
				
				if(index.equals(4)){
					misc[miscCount] = new MiscCell("Φόρος Εισοδήματος. Πλήρωσε 200", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Φόρος Εισοδήματος. Πλήρωσε 200");
					index++;
					
				}
				
				if(index.equals(38)) {
					misc[miscCount] = new MiscCell("Φόρος Πολυτελείας. Πλήρωσε 100", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Φόρος Πολυτελείας. Πλήρωσε 100");
					index++;
					
				}
				
				if(index.equals(10)) {
					misc[miscCount] = new MiscCell("Φυλακή", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Φυλακή");
					index++;
					
				}
				
				if(index.equals(30)) {
					misc[miscCount] = new MiscCell("Πήγαινε στην φυλακή", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Πήγαινε στην φυλακή");
					index++;
					continue;
				}
				
				if(index.equals(20)) {
					misc[miscCount] = new MiscCell("Ελεύθερη Στάθμευση", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Ελεύθερη Στάθμευση");
					index++;
					continue;
				}
				
				if(index.equals(0)) {
					misc[miscCount] = new MiscCell("Αφετηρία", index);
					miscCount++;
					//System.out.printf("Cell %d added %s\n", index, "Αφετηρία");
					index++;
					continue;
				}
				
				if(index.equals(5) || index.equals(15) || index.equals(25) || index.equals(35)) {
					String name = stationsInput.nextLine();
					stations[stationCount] = new RailroadCell(name, index);
					stationCount++;
					//System.out.printf("Cell %d added station %s\n", index, name);
					index++;
					continue;
				}
				
				if(index.equals(12)) {
					utilities[utilityCount] = new UtilityCell("Υπηρεσία Ηλεκτρισμού", 150, 75, index);
					utilityCount++;
					index++;
					//System.out.printf("Cell %d added utility %s\n", index-1, utilities[utilityCount-1].getTitle());
					continue;
				}

				if(index.equals(27)) {
					utilities[utilityCount] = new UtilityCell("Υπηρεσία Ύδρευσης", 150, 75, index);
					utilityCount++;
					index++;
					//System.out.printf("Cell %d added utility %s\n", index-1, utilities[utilityCount-1].getTitle());
					continue;
				}
				tokens = getTokens(propertyInput.nextLine());
				
				properties[propertyCount] = new PropertyCell(tokens.get(1),
						Integer.parseInt(tokens.get(2)),
						(Integer.parseInt(tokens.get(2))/2),
						Integer.parseInt(tokens.get(4)),
						tokens.get(0),
						Integer.parseInt(tokens.get(3)),
						index
						);

				propertyCount++;
				//System.out.printf("Cell %d added %s Property count: %d\n", index, tokens.get(1), propertyCount);
				index++;
			}
			
			stationsInput.close();
			propertyInput.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private List<String> getTokens(String str) {
	    List<String> tokens = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(str, ":");
	    while (tokenizer.hasMoreElements()) {
	        tokens.add(tokenizer.nextToken());
	    }
	    return tokens;
	}
	
	public String getTitle(int id) {
		int index=0;
		//System.out.println("Inputed id: " + id);
		for(index=0; index<this.getProperties().length; index++) {
			
			if(id == this.getProperties()[index].getId()) return this.getProperties()[index].getTitle();
		}
		
		for(index=0; index<this.getMiscCells().length; index++) {
			
			if(id == this.getMiscCells()[index].getId()) return this.getMiscCells()[index].getTitle();
		}
		
		for(index=0; index<this.getCardCells().length; index++) {
			
			if(id == this.getCardCells()[index].getId()) return this.getCardCells()[index].getTitle();
		}
		
		for(index=0; index<this.getRailroadCells().length; index++) {
			
			if(id == this.getRailroadCells()[index].getId()) return this.getRailroadCells()[index].getTitle();
		}
		
		for(index=0; index<this.getUtilityCells().length; index++) {
			
			if(id == this.getUtilityCells()[index].getId()) return this.getUtilityCells()[index].getTitle();
		}
		
		return "Not found";
		
	}
}
