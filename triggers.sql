delimiter //
DROP TRIGGER IF EXISTS Compras//
CREATE TRIGGER Compras
AFTER INSERT on FacturaCompra
FOR EACH ROW
BEGIN
       
        Declare cantidad float;
        set cantidad=(select sum(Total_Factura) from FacturaCompra where Anulado=false);       
        
        Update	 Cuentas set Cuentas.Compras=cantidad where Cuentas.id=1;
        
    END;//
delimiter ;  

delimiter //
DROP TRIGGER IF EXISTS ComprasRestar//
CREATE TRIGGER ComprasRestar
AFTER Update on FacturaCompra
FOR EACH ROW
BEGIN
       
        Declare cantidad float;
        set cantidad=(select sum(Total_Factura) from FacturaCompra where Anulado=false);       
        
        Update	 Cuentas set Cuentas.Compras=cantidad where Cuentas.id=1;
        
    END;//
delimiter ;  

delimiter //
DROP TRIGGER IF EXISTS VentasRestar//
CREATE TRIGGER VentasRestar
AFTER Update on FacturaVenta
FOR EACH ROW
BEGIN
       
        Declare cantidad float;
        set cantidad=(select sum(Total) from FacturaVenta where Anulado=false);       
        
        Update	 Cuentas set Cuentas.Ventas=cantidad where Cuentas.id=1;
        
    END;//
delimiter ;  

delimiter //
DROP TRIGGER IF EXISTS Ventas//
CREATE TRIGGER Ventas
AFTER INSERT on FacturaVenta
FOR EACH ROW
BEGIN
        
	    Declare cantidad float;
        set cantidad=(select sum(Total) from FacturaVenta);       
        
        Update	 Cuentas set Cuentas.Ventas=cantidad where Cuentas.id=1;
        
        
    END;//
delimiter ;  

delimiter //
DROP TRIGGER IF EXISTS SumarExistencias//
CREATE TRIGGER SumarExistencias
AFTER INSERT on Lote
FOR EACH ROW
BEGIN
        
 Update	 Producto set Producto.Existencia=Producto.Existencia+new.Cantidad where Producto.id=new.Producto_id;
                
        
    END;//
delimiter ;  

delimiter //
DROP TRIGGER IF EXISTS RestarExistencias//
CREATE TRIGGER RestarExistencias
AFTER INSERT on LoteVenta
FOR EACH ROW
BEGIN
        
        
Update	 Producto  set 
Producto.Existencia=Producto.Existencia-new.Cantidad 
where Producto.id=new.Producto_id;   
    END;//
delimiter ;  

