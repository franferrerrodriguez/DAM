using System;
using System.Collections.Generic;

namespace GestorRetos
{
    public class Reto
    {
        private int codigo;
        private string titulo;
        private int dificultad;

        public Reto(int codigo, string titulo, int dificultad)
        {
            SetCodigo(codigo);
            SetTitulo(titulo);
            SetDificultad(dificultad);
        }

        public int GetCodigo()
        {
            return codigo;
        }

        public void SetCodigo(int codigo)
        {
            this.codigo = codigo.ToString().Length <= 3 ? codigo : this.codigo;
        }

        public string GetTitulo()
        {
            return titulo;
        }

        public void SetTitulo(string titulo)
        {
            this.titulo = titulo;
        }

        public int GetDificultad()
        {
            return dificultad;
        }

        public void SetDificultad(int dificultad)
        {
            this.dificultad = DificultadValida(dificultad) ? dificultad : this.dificultad;
        }

        public bool EsValido()
        {
            bool valido = false;

            if(codigo != 0 && titulo != "" && dificultad != 0)
                valido = true;

            return valido;
        }

        public void Imprimir()
        {
            Console.WriteLine("{0}: {1} (Dificultad: {2})", 
                codigo, titulo, dificultad);
        }

        public static Reto TextToReto(string reto_text)
        {
            Reto reto = null;
            string[] split = reto_text.Split(':');
            if (split.Length.Equals(3))
                if (Int32.TryParse(split[0], out int codigo) && 
                    Int32.TryParse(split[2], out int dificultad))
                    reto = new Reto(codigo, split[1], dificultad);

            return reto;
        }

        public static bool DificultadValida(int dificultad)
        {
            bool dificultad_valida = false;

            if (dificultad.Equals(1) ||
                dificultad.Equals(2) ||
                dificultad.Equals(3) ||
                dificultad.Equals(4) ||
                dificultad.Equals(5))
            {
                dificultad_valida = true;
            }

            return dificultad_valida;
        }
    }
}