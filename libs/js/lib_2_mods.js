function Mod(rname, version, author, desc, dname) {
	this.rname = rname;
	this.version = version;
	this.author = author;
	this.desc = desc;
	this.dname = dname;
	this.on_load = function() {
	}
	this.on_unload = function() {
	}
}

function EventRegistry(handlers...) {
	this.handlers = handlers;
}

/**
* A struct that stores a event name, its params
*/
function EventRegistryHandler(name, paramsProto) {
	this.name = name;
	this.paramsProto = paramsProto;
}

function register_mod(mod) {
	if (!(mod instanceof Mod)) {
		dlog.warning("Invalid mod specified: " + mod);
		return;
	}
	ntv_Submit(mod);
}

function unregister_mod(mod) {
	if (!(mod instanceof Mod)) {
		dlog.warning("Invalid mod specified: " + mod);
		return;
	}
	ntv_Kick(mod);
}
