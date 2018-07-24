
	var cvs,ctx;
	var W,H;
	var bgPic=new Image(),
		starPic=new Image();
	var num,
		starArr=[];
	var last,
		now,
		interval;
	function init(){
		cvs=document.getElementById('cvs');
		ctx=cvs.getContext('2d');
		
		W=document.documentElement.clientWidth;
		H=document.documentElement.clientHeight;
		
		cvs.width=W;
		cvs.height=H;
		
		bgPic.src="bg1.jpg";
		starPic.src="star.png";
		
		last=Date.now();
		
		num=80;
		for(var i=0;i<num;i++){
			starArr[i]=new Star();
			starArr[i].ini();
		}
		
		gameLoop();
	}
	
	function draw(){
		ctx.fillRect(0,0,W,H);
		ctx.drawImage(bgPic,0,0,W,H);
		for(var i=0;i<num;i++){
			starArr[i].update();
			starArr[i].drawStar();
		}
	}
	
	function gameLoop(){
		window.requestAnimationFrame(gameLoop);
		
		now=Date.now();
		interval=now-last;
		last=now;
		
		draw();
	}
	
	function Star(){
		this.x;
		this.y;
		
		this.no;
		this.timer;
		
		this.xSpeed;
		this.ySpeed;
	}
	Star.prototype.ini=function(){
		this.xSpeed=Math.random()*4-2;
		this.ySpeed=Math.random()*4-2;
		
		this.x=Math.random()*W;
		this.y=Math.random()*H;
		
		this.no=Math.floor(Math.random()*7);
		this.timer=0;
	}
	Star.prototype.update=function(){
		this.x+=this.xSpeed*interval*0.005;
		this.y+=this.ySpeed*interval*0.005;
		
		this.timer+=interval;
		if(this.timer>30){
			this.no++;
			if(this.no>6){
				this.no=0;
			}
			this.timer=0;
		}
		
		
	}
	Star.prototype.drawStar=function(){
		ctx.drawImage(starPic,this.no*7,0,7,7,this.x,this.y,7,7);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	