function Modification(rname, version, author, desc, dname) {
    this.rname = rname;
    this.version = version;
    this.author = author;
    this.desc = desc;
    this.dname = dname;
    this.on_load = function () { }
    this.on_unload = function () { }
}

function register_mod(mod) {
    
}