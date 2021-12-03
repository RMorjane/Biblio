let ctn = document.getElementById("ctn")

document.addEventListener('DOMContentLoaded', nav)

function nav(){

    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.main-nav');
    
    burger.addEventListener('click', ()=>{
        nav.classList.toggle('show')
    })

}

function $_GET(param) {
	var vars = {};
	window.location.href.replace( location.hash, '' ).replace( 
		/[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
		function( m, key, value ) { // callback
			vars[key] = value !== undefined ? value : '';
		}
	);

	if ( param ) {
		return vars[param] ? vars[param] : null;	
	}
	return vars;
}

function setUrl(){
    var bean = $_GET("bean")

    if(bean !=null){
        ctn.setAttribute("src","ServletGestion"+bean)
    }
}

setUrl();