using System;
using System.Collections.Generic;

/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 4
Ejercicio 1 - Ikea
Apartado 1.1 si
Apartado 1.2 si
Apartado 1.3 si
Apartado 1.4 si
Apartado 1.5 si
Apartado 1.6 si
Apartado 1.7 si
Apartado 1.8 si
*/
namespace Ikea
{
    class Ikea
    {
        public struct Producto{
            public String cod;
            public String descripcion;
            public String marca;
            public double precio;
            public Ubicacion ubicacion;
        }

        public struct Ubicacion{
            public int pasillo;
            public int seccion;
        }

        public static void Main(string[] args)
        {
            Producto[] listaProductos = new Producto[50];
            int pos = 0;
            int opcion = 0;

            while (opcion != 6){

                opcion = 0;

                Console.WriteLine("############################");
                Console.WriteLine("###### MENÚ PRINCIPAL ######");
                Console.WriteLine("####--------------------####");
                Console.WriteLine("### 1. Añadir producto   ###");
                Console.WriteLine("### 2. Borrar producto   ###");
                Console.WriteLine("### 3. Buscar producto   ###");
                Console.WriteLine("### 4. Ordenar productos ###");
                Console.WriteLine("### 5. Calcular importe  ###");
                Console.WriteLine("### 6. Salir             ###");
                Console.WriteLine("######----------------######");
                Console.WriteLine("############################");

                if (opcion < 1 || opcion > 6)
                {
                    try
                    {
                        Console.Write("\nSelecciona opción del menú: ");
                        opcion = Convert.ToInt32(Console.ReadLine());
                    }
                    catch (FormatException) { }
                    if (opcion < 1 || opcion > 6)
                    {
                        Console.Clear();
                        Console.WriteLine("La opción introducida no es válida. Debe introducir un número entre 1 y 6.\n");
                    }else if (pos == 0 && opcion != 1 && opcion != 6)
                    {
                        Console.Clear();
                        Console.WriteLine("No existen artículos en el sistema. Debe introducir alguno primero.\n");
                        opcion = 0;
                    }

                }

                switch (opcion)
                {
                    case 1:
                        crearTitulo("AÑADIR PRODUCTO");

                        Producto producto = new Producto();
                        while (null == producto.cod || producto.cod.Equals("") || existeCodigo(listaProductos, producto.cod))
                        {
                            Console.Write("Introduce código del producto (Pulse 'c' para cancelar): ");
                            producto.cod = Console.ReadLine();
                            if (producto.cod.Equals(""))
                            {
                                Console.WriteLine("Error. No ha introducido ningún código de producto.\n");
                            }else if (existeCodigo(listaProductos, producto.cod))
                            {
                                Console.WriteLine("Error. El código de producto introducido ya existe en el sistema.\n");
                            }
                        }

                        if (producto.cod.Equals("c"))
                        {
                            Console.Clear();
                            break;
                        }

                        while (null == producto.descripcion || producto.descripcion.Equals(""))
                        {
                            Console.Write("Introduce la descripción del producto: ");
                            producto.descripcion = Console.ReadLine();
                            if (producto.descripcion.Equals(""))
                            {
                                Console.WriteLine("Error. No ha introducido ninguna descripción de producto.\n");
                            }
                        }

                        while (null == producto.marca || producto.marca.Equals(""))
                        {
                            Console.Write("Introduce la marca del producto: ");
                            producto.marca = Console.ReadLine();
                            if (producto.marca.Equals(""))
                            {
                                Console.WriteLine("Error. No ha introducido ninguna marca de producto.\n");
                            }
                        }

                        bool precio = false;
                        while (!precio)
                        {
                            Console.Write("Introduce el precio del producto: ");
                            try
                            {
                                producto.precio = Convert.ToDouble(Console.ReadLine().Replace(".", ","));
                                precio = true;
                            }
                            catch (FormatException)
                            {
                                Console.WriteLine("Debe introducir un precio válido para el producto.\n");
                            }
                        }

                        Ubicacion ubicacion = new Ubicacion();
                        bool pasilloUbicacion = false;
                        while (!pasilloUbicacion)
                        {
                            Console.Write("Introduce el pasillo de la ubicación del producto: ");
                            try
                            {
                                ubicacion.pasillo = Convert.ToInt32(Console.ReadLine());
                                if(ubicacion.pasillo >= 0 && ubicacion.pasillo <= 40)
                                {
                                    producto.ubicacion.pasillo = ubicacion.pasillo;
                                    pasilloUbicacion = true;
                                }
                                else
                                {
                                    Console.WriteLine("Error. Debe introducir número de pasillo entre 0 y 40.\n");
                                }
                            }
                            catch (FormatException)
                            {
                                Console.WriteLine("Debe introducir un número de pasillo válido para la ubicación del producto.\n");
                            }
                        }

                        bool seccionUbicacion = false;
                        while (!seccionUbicacion)
                        {
                            Console.Write("Introduce la sección de la ubicación del producto: ");
                            try
                            {
                                ubicacion.seccion = Convert.ToInt32(Console.ReadLine());
                                if (ubicacion.seccion >= 0 && ubicacion.seccion <= 30)
                                {
                                    producto.ubicacion.seccion = ubicacion.seccion;
                                    seccionUbicacion = true;
                                }
                                else
                                {
                                    Console.WriteLine("Error. Debe introducir número de sección entre 0 y 30.\n");
                                }
                            }
                            catch (FormatException)
                            {
                                Console.WriteLine("Debe introducir un número de sección válido para la ubicación del producto.\n");
                            }
                        }

                        listaProductos[pos] = producto;

                        Console.Clear();

                        Console.WriteLine("Producto añadido correcto en la posición: [{0}] de la lista.\n", pos);

                        pos++;
                        break;
                    case 2:
                        crearTitulo("BORRAR PRODUCTO");

                        Console.WriteLine("Lista de productos actuales en la lista: (Total: {0})", pos);

                        foreach (Producto pro in listaProductos)
                        {
                            if (pro.cod != null)
                            {
                                Console.WriteLine("Código: {0} | Descripción: {1} | Marca: {2}", pro.cod, pro.descripcion, pro.marca);
                            }
                        }

                        String codigo = "";
                        while (!existeCodigo(listaProductos, codigo))
                        {
                            Console.Write("\nIntroduce el código del producto que desea borrar (Pulse 'c' para cancelar): ");

                            codigo = Console.ReadLine();

                            if (codigo.Equals("c"))
                            {
                                Console.Clear();
                                break;
                            }else if (codigo.Equals(""))
                            {
                                Console.WriteLine("Error. No ha introducido ningún código de producto.\n");
                            }else if(!existeCodigo(listaProductos, codigo))
                            {
                                Console.WriteLine("Producto no encontrado.\n");
                            }
                            else
                            {
                                listaProductos = borrarProducto(listaProductos, codigo);
                                pos--;
                                Console.Clear();
                                Console.WriteLine("Producto borrado correctamente de la lista.\n");
                                break;
                            }
                        }

                        break;
                    case 3:
                        crearTitulo("BUSCAR PRODUCTOS");

                        String descripcion = "";
                        while (descripcion.Equals(""))
                        {
                            Console.Write("Introduce parte de la descripción del producto (Pulse 'c' para cancelar): ");

                            descripcion = Console.ReadLine();

                            if (descripcion.Equals("c"))
                            {
                                Console.Clear();
                                break;
                            }
                            else if (descripcion.Equals(""))
                            {
                                Console.WriteLine("Error. No ha introducido ninguna descripción de producto.\n");
                            }
                            else
                            {
                                Console.Clear();
                                List<Producto> coincidencias = buscarProductosDescripcion(listaProductos, descripcion);
                                Console.WriteLine("Se han encontrado total de: {0} coincidencias con la descripción: '{1}'.", coincidencias.Count, descripcion);
                                foreach (Producto pro in coincidencias)
                                {
                                    Console.WriteLine("{0} {1} {2} {3:n}", pro.cod, pro.descripcion, pro.marca, Math.Round(pro.precio, 2));
                                }
                                Console.WriteLine("");
                            }
                        }

                        break;
                    case 4:
                        crearTitulo("ORDENAR PRODUCTOS");

                        Console.WriteLine("Lista de productos ordenados:");
                        foreach (Producto pro in ordenarProductos(listaProductos))
                        {
                            if(pro.cod != null)
                            {
                                Console.WriteLine("{0} {1} {2:n} (pasillo {3} sección {4})", pro.cod, pro.descripcion, Math.Round(pro.precio, 2), pro.ubicacion.pasillo, pro.ubicacion.seccion);
                            }
                        }

                        Console.WriteLine("");

                        break;
                    case 5:
                        crearTitulo("CALCULAR IMPORTE");

                        Console.WriteLine("El importe total de la lista de productos es: {0:n}.\n", calcularImporte(listaProductos));

                        break;
                    case 6:
                        crearTitulo("¡HASTA PRONTO!");
                        break;
                }

            }

        }

        public static void crearTitulo(String titulo)
        {
            Console.Clear();
            Console.WriteLine("###### {0} ######", titulo);
        }

        public static Boolean existeCodigo(Producto[] listaProductos, String cod)
        {
            foreach (Producto pro in listaProductos)
            {
                if (pro.cod != null && pro.cod.Equals(cod))
                {
                    return true;
                }
            }

            return false;
        }

        public static List<Producto> buscarProductosDescripcion(Producto[] listaProductos, String descripcion)
        {
            List<Producto> coincidencias = new List<Producto>();
            foreach (Producto pro in listaProductos)
            {
                if (pro.cod != null && pro.descripcion.ToUpper().Contains(descripcion.ToUpper()))
                {
                    coincidencias.Add(pro);
                }
            }

            return coincidencias;
        }

        public static Producto[] borrarProducto(Producto[] listaProductos, String cod)
        {
            bool s = false;
            for (int i = 0; i < listaProductos.Length; i++)
            {
                if (((listaProductos[i].cod != null && listaProductos[i].cod == cod) || s) && i < (listaProductos.Length - 1))
                {
                    s = true;
                    listaProductos[i] = listaProductos[i + 1];
                    listaProductos[i + 1] = new Producto();
                }
            }
            return listaProductos;
        }

        public static Producto[] ordenarProductos(Producto[] listaProductos)
        {
            Producto[] listaProductosTemp = listaProductos.Clone() as Producto[];
            Producto[] listaOrdenada = new Producto[listaProductosTemp.Length];
            Producto protemp = new Producto();

            for (int a = 0; a < listaProductos.Length; a++)
            {
                if (listaProductos[a].cod != null)
                {
                    for (int i = 0; i < listaProductosTemp.Length; i++)
                    {
                        if (i == 0)
                        {
                            protemp = listaProductosTemp[0];
                        }
                        else if (listaProductosTemp[i].cod != null)
                        {
                            if (listaProductosTemp[i].ubicacion.pasillo < protemp.ubicacion.pasillo)
                            {
                                protemp = listaProductosTemp[i];
                            }
                            else if (listaProductosTemp[i].ubicacion.pasillo == protemp.ubicacion.pasillo)
                            {
                                if (listaProductosTemp[i].ubicacion.seccion <= protemp.ubicacion.seccion)
                                {
                                    protemp = listaProductosTemp[i];
                                }
                            }
                        }
                    }
                    listaOrdenada[a] = protemp;
                    borrarProducto(listaProductosTemp, protemp.cod);
                }
                
            }

            return listaOrdenada;
        }

        public static double calcularImporte(Producto[] listaProductos)
        {
            double importetotal = 0;
            foreach(Producto p in listaProductos)
            {
                importetotal += p.precio;
            }
            return Math.Round(importetotal, 2);
        }

    }
}