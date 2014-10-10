package piano;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
	PianoMachine pm = new PianoMachine();
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0, midi.history());
    }
    
    @Test
    public void singleRepeatedNoteTest() throws MidiUnavailableException {
        String expected0 = "on(61,PIANO) wait(0) on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
        Midi.wait(0);
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0, midi.history());
    }
    
    @Test
    public void twoNotesTest() throws MidiUnavailableException {
    	String expected1 =
    		"on(71,PIANO) wait(0) on(65,PIANO) wait(100) off(65,PIANO) wait(0) off(71,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(100);
    	pm.endNote(new Pitch(5));
    	Midi.wait(0);
    	pm.endNote(new Pitch(11));
    	
    	System.out.println(midi.history());
    	assertEquals(expected1, midi.history());
    }
    
    @Test
    public void twoRepeatedNotesTest() throws MidiUnavailableException {
    	String expected1 = "on(71,PIANO) wait(0) on(65,PIANO) wait(0) on(71,PIANO) "+
    			"wait(0) on(65,PIANO) wait(100) off(65,PIANO) wait(0) off(71,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(100);
    	pm.endNote(new Pitch(5));
    	Midi.wait(0);
    	pm.endNote(new Pitch(11));
    	
    	System.out.println(midi.history());
    	assertEquals(expected1, midi.history());
    }
    
    @Test
    public void switchToBrightPianoTest() throws MidiUnavailableException {
    	String expected2 = "on(61,BRIGHT_PIANO) wait(100) off(61,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
        pm.changeInstrument();
        midi.clearHistory();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
		System.out.println(midi.history());
        assertEquals(expected2, midi.history());
    }
    
    @Test
    public void switchToElectricGrandTest() throws MidiUnavailableException {
    	String expected3 = "on(61,ELECTRIC_GRAND) wait(100) off(61,ELECTRIC_GRAND)";
    	
    	Midi midi = Midi.getInstance();
    	
        pm.changeInstrument();
        pm.changeInstrument();
        midi.clearHistory();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
		System.out.println(midi.history());
        assertEquals(expected3, midi.history());
    }
    
    @Test
    public void switchToMusicBoxTest() throws MidiUnavailableException {
    	String expected4 = "on(61,MUSIC_BOX) wait(100) off(61,MUSIC_BOX)";
    	
    	Midi midi = Midi.getInstance();
    	
    	// switch continuously for 10 times, until the instrument
    	// change to MUSICAL_BOX
    	
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
		System.out.println(midi.history());
        assertEquals(expected4, midi.history());
    }
}
