delimiter //
DROP TRIGGER IF EXISTS SumarExistencias//
CREATE TRIGGER SumarExistencias
AFTER INSERT on Lote
FOR EACH ROW
BEGIN
        
        
        
        Update	 Producto set Producto.Existencia=Producto.Existencia+new.Cantidad where Producto.id=new.Producto_id;
        
    END;//
delimiter ;  