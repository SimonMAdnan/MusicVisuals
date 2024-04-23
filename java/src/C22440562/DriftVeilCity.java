package C22440562;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ie.tudublin.Visual;

public class DriftVeilCity extends Visual
{
   
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    int height = 1000;
    int width = 1000;

    FFT fft;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(height, width, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        background(0);
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("DriftveilCity.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];


        x1 = random(0, width);
		x2 = random(0, width);
		y1 = random(0, height);
		y2 = random(0, height);

		float range = 5;

		x1dir = random(-range, range);
		x2dir = random(-range, range);
		y1dir = random(-range, range);
		y2dir = random(-range, range);

		smooth();
    } 

    float x1, y1, x2, y2;
	float x1dir, x2dir, y1dir, y2dir;
	float c = 0;

    public void background1(){
        stroke(255);
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            float hue = map(i, 0, ab.size() , 0, 256);
            stroke(hue, 255, 255);
            fill(hue,255,255);
            
            circle(random(1000), random(1000), ab.get(i) * 40);
            
        }
    }

    public void drawBounce()
    {
        strokeWeight(2);
		stroke(255);
        fill(255);
		c = (c + 1f) % 255;
	    ellipse(x1, y1, x2, y2);    

		x1 += x1dir;
		x2 += x2dir;
		y1 += y1dir;
		y2 += y2dir;
		
		if (x1 < 0 || x1 > width)
		{
			x1dir = - x1dir;
		}
		if (y1 < 0 || y1 > height)
		{
			y1dir = - y1dir;
		}

		if (x2 < 0 || x2 > width)
		{
			x2dir = - x2dir;
		}
		if (y2 < 0 || y2 > height)
		{
			y2dir = - y2dir;
		}

    }


    public void draw(){
        colorMode(HSB);
        background(0);
        background1();
        drawBounce();
    }
}