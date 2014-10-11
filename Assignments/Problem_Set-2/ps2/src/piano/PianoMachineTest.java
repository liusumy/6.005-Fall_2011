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
    
    @Test
    public void shiftUpByOneOctaveTest() throws MidiUnavailableException {
    	String expected0 = "on(72,PIANO) wait(100) off(72,PIANO)";
    	String expected1 = "on(83,PIANO) wait(100) off(83,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());
    }
    
    @Test
    public void shiftUpByTwoOctavesTest() throws MidiUnavailableException {
    	String expected0 = "on(84,PIANO) wait(100) off(84,PIANO)";
    	String expected1 = "on(95,PIANO) wait(100) off(95,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftUpBoundaryTest() throws MidiUnavailableException {
    	String expected0 = "on(84,PIANO) wait(100) off(84,PIANO)";
    	String expected1 = "on(95,PIANO) wait(100) off(95,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	pm.shiftUp();
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftDownByOneOctaveTest() throws MidiUnavailableException {
    	String expected0 = "on(48,PIANO) wait(100) off(48,PIANO)";
    	String expected1 = "on(59,PIANO) wait(100) off(59,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());
    }
    
    @Test
    public void shiftDownByTwoOctavesTest() throws MidiUnavailableException {
    	String expected0 = "on(36,PIANO) wait(100) off(36,PIANO)";
    	String expected1 = "on(47,PIANO) wait(100) off(47,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftDownBoundaryTest() throws MidiUnavailableException {
    	String expected0 = "on(36,PIANO) wait(100) off(36,PIANO)";
    	String expected1 = "on(47,PIANO) wait(100) off(47,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	pm.shiftDown();
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
		System.out.println(midi.history());
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
		System.out.println(midi.history());
        assertEquals(expected1, midi.history());;
    }
}
