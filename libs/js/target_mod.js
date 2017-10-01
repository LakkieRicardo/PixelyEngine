function load(mod) {
    var box = UtilBox.asset.load(mod, "/sprites/box.png");
    // First is render function, second is init function, third is preUpdate,
    // fourth is update, fifth is postUpdate
    var EntityBox = UtilBox.entity.make(function() {
        RenderBox.engine().draw(box, this.pos);
    }, function(params) {
        // Here we set the position to position
        this.pos = params.pos;
        // Find static rigidbody type and make it
        this.addComponent(Physics.type("static_rigidbody").make());
        mod.entities.push(this); // Add the current entity to the mod entities(so we can unload them later)
    }, function() {}, function() {}, function() {});
    var entity = EntityBox.instantiate({pos: new Vector2(50, 30)}); // This entity requires just a position
}

function unload(mod) {
    for (var i = 0; i < mod.entities.length; i++) {
        UtilBox.entity.delete(mod.entities[i]);
    }
    delete mod.entities;
}

// Load callback, unload callback, display name, real name, version, author
modr.make(load, unload, "Test Mod", "testm", "1.0", "Lakkie");