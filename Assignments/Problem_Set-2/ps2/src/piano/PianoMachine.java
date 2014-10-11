package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;
import music.PitchLevel;

public class PianoMachine {
	
	private Midi midi;
	private midi.Instrument instrument = Midi.DEFAULT_INSTRUMENT;
	
	private PitchLevel pitchLevel = PitchLevel.DEFAULT_LEVEL;
	
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    /**
     * Turn an note event associated with the specified pitch.
     * @param rawPitch: the frequency of a musical note, required to
     * 					be among the set {0, 1, 2, ..., 10, 11}
     */
    public void beginNote(Pitch rawPitch) {
    	Pitch modulatedPitch = rawPitch;
    	switch (pitchLevel) {
    	case DEFAULT_LEVEL:
    		modulatedPitch = rawPitch;
    		break;
    	case ONE_OCTAVE_BELOW:
    		modulatedPitch = rawPitch.transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case TWO_OCTAVES_BELOW:
    		modulatedPitch =
    			rawPitch.transpose((Pitch.OCTAVE) * (-1)).transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case ONE_OCTAVE_ABOVE:
    		modulatedPitch = rawPitch.transpose(Pitch.OCTAVE);
    		break;
    	case TWO_OCTAVES_ABOVE:
    		modulatedPitch =
    			rawPitch.transpose(Pitch.OCTAVE).transpose(Pitch.OCTAVE);
    		break;
    	}
    	midi.beginNote(modulatedPitch.toMidiFrequency(), instrument);
    }
    
    /**
     * Turn off an note event associated with the specified pitch.
     * @param rawPitch: the frequency of a musical note, required to
     * 					be among the set {0, 1, 2, ..., 10, 11}
     */
    public void endNote(Pitch rawPitch) {
    	Pitch modulatedPitch = rawPitch;
    	switch (pitchLevel) {
    	case DEFAULT_LEVEL:
    		modulatedPitch = rawPitch;
    		break;
    	case ONE_OCTAVE_BELOW:
    		modulatedPitch = rawPitch.transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case TWO_OCTAVES_BELOW:
    		modulatedPitch =
    			rawPitch.transpose((Pitch.OCTAVE) * (-1)).transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case ONE_OCTAVE_ABOVE:
    		modulatedPitch = rawPitch.transpose(Pitch.OCTAVE);
    		break;
    	case TWO_OCTAVES_ABOVE:
    		modulatedPitch =
    			rawPitch.transpose(Pitch.OCTAVE).transpose(Pitch.OCTAVE);
    		break;
    	}
    	midi.endNote(modulatedPitch.toMidiFrequency(), instrument);
    }
    
    /**
     * Switch the instrument mode to the next instrument among list
     * of musical instruments. If the current instrument is the last
     * one in the musical instrument list, switch back to the first
     * instrument.
     */
    public void changeInstrument() {
    	instrument = instrument.next();
    }
    
    /**
     * Shift the notes that the keys play up, respectively, by one
     * octave (12 semitones).
     * Any note can be shifted up at most by two octaves, from its
     * starting pitch.
     */
    public void shiftUp() {
    	switch (pitchLevel) {
    	case TWO_OCTAVES_BELOW:
    		pitchLevel = PitchLevel.ONE_OCTAVE_BELOW;
    		return;
    	case ONE_OCTAVE_BELOW:
    		pitchLevel = PitchLevel.DEFAULT_LEVEL;
    		return;
    	case DEFAULT_LEVEL:
    		pitchLevel = PitchLevel.ONE_OCTAVE_ABOVE;
    		return;
    	case ONE_OCTAVE_ABOVE:
    		pitchLevel = PitchLevel.TWO_OCTAVES_ABOVE;
    		return;
    	case TWO_OCTAVES_ABOVE:
    		return;
    	}
    }
    
    /**
     * Shift the notes that the keys play down, respectively, by one
     * octave (12 semitones).
     * Any note can be shifted down at most by two octaves, from its
     * starting pitch.
     */
    public void shiftDown() {
    	switch (pitchLevel) {
    	case TWO_OCTAVES_BELOW:
    		return;
    	case ONE_OCTAVE_BELOW:
    		pitchLevel = PitchLevel.TWO_OCTAVES_BELOW;
    		return;
    	case DEFAULT_LEVEL:
    		pitchLevel = PitchLevel.ONE_OCTAVE_BELOW;
    		return;
    	case ONE_OCTAVE_ABOVE:
    		pitchLevel = PitchLevel.DEFAULT_LEVEL;
    		return;
    	case TWO_OCTAVES_ABOVE:
    		pitchLevel = PitchLevel.ONE_OCTAVE_ABOVE;
    		return;
    	}
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
    	return false;
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    protected void playback() {    	
        //TODO: implement for question 4
    }

}
