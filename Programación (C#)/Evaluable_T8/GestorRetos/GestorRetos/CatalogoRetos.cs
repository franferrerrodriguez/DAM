using System;
using System.Collections.Generic;

namespace GestorRetos
{
    public class CatalogoRetos
    {
        private Dictionary<int, Reto> catalogo;

        public CatalogoRetos()
        {
            catalogo = new Dictionary<int, Reto>();
        }

        public bool NuevoReto(Reto reto)
        {
            if (reto.EsValido())
                catalogo.Add(reto.GetCodigo(), reto);

            return reto.EsValido();
        }

        public Reto ObtenerReto(int codigo_reto)
        {
            if (catalogo.ContainsKey(codigo_reto))
                return catalogo[codigo_reto];

            return null;
        }

        public void ListarPorDificultad(int dificultad_reto)
        {
            bool busqueda = false;
            foreach (var item in catalogo)
                if (item.Value.GetDificultad().Equals(dificultad_reto))
                {
                    busqueda = true;
                    item.Value.Imprimir();
                }

            if (!busqueda)
                Console.WriteLine("No se encontraron retos disponibles\n");
        }

        public Reto PedirReto()
        {
            Reto reto = null;

            Console.Write("Introduce un código de reto: ");

            if (Int32.TryParse(Console.ReadLine(), out int codigo_reto) && codigo_reto.ToString().Length <= 3)
                reto = ObtenerReto(codigo_reto);
            else
                Console.WriteLine("Debe introducir un código numérico de 3 dígitos\n");

            return reto;
        }

    }
}