public class TestMod extends JavaMod {
    
    public void onLoad() {
        
    }
    
    public void onUnload() {
        
    }
    
    public static class EntityBox extends Entity {
        
        public EntityBox(Vector2i pos) {
            super.pos = pos;
            super.addComponent(Physics.instantiate("static_rigidbody"));
        }
        
    }
    
}