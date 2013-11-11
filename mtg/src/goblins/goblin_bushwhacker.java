package goblins;

import java.util.ArrayList;

public class goblin_bushwhacker extends Creature {

	int power,toughness,cmc,red; //red = amount of red mana needed to play
	String supertype,type;
	Boolean tapped; 
	
	public goblin_bushwhacker() {
		super(1, 1, 2, "creature", "goblin");
		red=2;
		
		//NOTE: actual cmc is 1. actual red is 1 but to
		//simplify, it will always be played kicked
		
	}

	@Override
	public void upkeep() {
		tapped = false;

	}

	@Override
	public int attack(){
		tapped = true;
		return power;
	}

	@Override
	public void play(ArrayList<Card> dest) {
		dest.add(this);
		Creature c;
		
		//creates a copy of each creature, increments
		//their power by one, removes the old from 
		//battle field, and replaces with new one.
		//TODO: will not work if you play multiple bushwhackers in one turn
		if(!dest.get(0).toString().equals("")){ //kicks as long as it isn't played to the graveyard
			for(int i = 0 ; i < dest.size() ; i++){
				c = (Creature) dest.get(i);
				c.power++;
				c.sick=false; //gives them haste
				dest.remove(i);
				dest.add(c);
				
			}
		}
		else{
			dest.add(this);
		}
	}

	@Override
	public String toString() {
		return "Goblin Bushwhacker";
	}
	
	public void end(){
		power = 1;
		toughness = 1;
		if(sick){
			sick = false;
		}
	}

}
