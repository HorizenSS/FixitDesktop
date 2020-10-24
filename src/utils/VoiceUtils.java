/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceUtils {
    private final Voice voice;
    private final String name;

    public VoiceUtils( String name) {
        this.name = name;
        this.voice = VoiceManager.getInstance().getVoice(this.name);
        this.voice.allocate();
    }
    public void say(String s){
        this.voice.speak(s);
    }
    public void sayMultiple(String[] s){
        for (String item : s) {
            this.voice.speak(item); 
        }
    }
    
    
    
}
