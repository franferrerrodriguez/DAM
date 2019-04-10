using System;
namespace HundirLaFlota
{
    /*
    * Ferrer Rodríguez, Francisco José
    * Practica Evaluable Tema 7
    *
    * La clase Barco implementará lo necesario para trabajar con barcos y cada barco estará
    * compuesta por un array de la clase Casilla   
    */
    public abstract class Barco
    {
        public enum Tipo
        {
            LANCHA = 1,
            FRAGATA = 2,
            BUQUE = 3,
            PORTAAVIONES = 4
        }

        public enum Orientacion
        {
            HORIZONTAL,
            VERTICAL
        }

        private Casilla[] casillas;
        private Tipo tipo;
        private int tamano;
        private Orientacion orientacion;

        public Barco(Tipo tipo)
        {
            this.tipo = tipo;
            tamano = (int) tipo;
            casillas = new Casilla[tamano];
        }

        public Casilla[] GetCasillas()
        {
            return casillas;
        }

        public void SetCasillas(int array_pos, Casilla casilla)
        {
            if(array_pos <= tamano)
                casillas[array_pos] = casilla;
        }

        public bool EstaTocado()
        {
            bool tocado = false;

            foreach (Casilla casilla in casillas)
            {
                if (casilla.GetEstado().Equals(Casilla.Estados.TOCADO))
                {
                    tocado = true;
                    break;
                }
            }

            return tocado;
        }

        public bool EstaHundido()
        {
            bool hundido = true;

            foreach(Casilla casilla in casillas)
            {
                if(null != casilla)
                {
                    if (!casilla.GetEstado().Equals(Casilla.Estados.TOCADO))
                    {
                        hundido = false;
                        break;
                    }
                }
            }

            return hundido;
        }

        public Tipo GetTipo()
        {
            return tipo;
        }

        public int GetTamano()
        {
            return tamano;
        }

        public Orientacion GetOrientacion()
        {
            return orientacion;
        }

        public void SetOrientacion(Orientacion orientacion)
        {
            this.orientacion = orientacion;
        }

    }
}