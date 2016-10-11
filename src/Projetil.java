import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Projetil extends Sprite {
	
	float velx;
	float vely;
	
    float raio = 2;
    
    Personagem pai = null;
    
    int dano = 50;

	public Projetil(int x,int y, float velX,float velY,Personagem pai) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.velx = velX;
		this.vely = velY;
		
		this.pai = pai;
	}
	
	@Override
	public void simulaSe(long DiffTime) {
		// TODO Auto-generated method stub
		float oldx = x;
		float oldy = y;
		
		x+=velx*DiffTime/1000.0;
		y+=vely*DiffTime/1000.0;
				
		int bx = (int)((x)/GamePanel.mapa.tileW);
		int by = (int)((y)/GamePanel.mapa.tileH);
		
		if(bx<0||by<0||bx>=GamePanel.mapa.Largura||by>=GamePanel.mapa.Altura||GamePanel.mapa.mapa2[by][bx]!=0){
			x = oldx;
			y = oldy;
			vivo = false;
		}
		
		if((x>GamePanel.mapa.Largura*GamePanel.mapa.tileW-24) || x <= 0){
			x = oldx;
			y = oldy;
			vivo = false;
		}
		if((y>GamePanel.mapa.Altura*GamePanel.mapa.tileH-32) || y <= 0){
			x = oldx;
			y = oldy;
			vivo = false;
		}
		
		for(int i = 0; i <GamePanel.listaDePersonagens.size();i++){
			Personagem pers = (Personagem)GamePanel.listaDePersonagens.get(i);
			if(pai!=pers&&colide(pers)){
				x = oldx;
				y = oldy;
				vivo = false;
				pers.levaDano(dano);
				break;
			}
		}
	}

	@Override
	public void desenhaSe(Graphics2D dbg,int telax,int telay){
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		dbg.fillRect( (int)x-telax-2,(int)y-telay-2, 4, 4);
	}
	
	public boolean colide(Personagem pers){
		float dx = (pers.x+pers.cx) - x;
		float dy = (pers.y+pers.cy) - y;
		float sraio = pers.raio+raio;
		
		if(sraio*sraio>(dx*dx+dy*dy)){
			return true;
		}
		return false;
	}
}
