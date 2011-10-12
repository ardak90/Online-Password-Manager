         jQuery(document).ready(
    		 function(){
    	         init();
    		 }
    		 );
    
    var clip = null;
	
	function $(id) { return document.getElementById(id); }
	
	function init() {
		clip = new ZeroClipboard.Client();
		clip.setHandCursor( true );
		
		clip.addEventListener('load', my_load);
		clip.addEventListener('mouseOver', my_mouse_over);
		clip.addEventListener('complete', my_complete);
		
		clip.glue( 'd_clip_button' );
	}
	
	function my_load(client) {
		debugstr("Flash movie loaded and ready.");
	}
	
	function my_mouse_over(client) {
		// we can cheat a little here -- update the text on mouse over
		clip.setText( $('fe_text').value );
	}
	
	function my_complete(client, text) {
		debugstr("Copied text to clipboard: " + text );
	}
	
	function debugstr(msg) {
		var p = document.createElement('p');
		p.innerHTML = msg;
		$('d_debug').appendChild(p);
	}