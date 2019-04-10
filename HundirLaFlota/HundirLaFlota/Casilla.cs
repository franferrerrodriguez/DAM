using System;
namespace HundirLaFlota
{
    /*
    * Ferrer Rodríguez, Francisco José
    * Practica Evaluable Tema 7
    *
    * La clase Casilla implementará lo necesario y serán 
    * piezas de la clase Barco   
    */
    public class Casilla
    {
        public struct Coords
        {
            public int fila;
            public int columna;

            public Coords(int fila, int columna)
            {
                this.fila = fila;
                this.columna = columna;
            }
        }

        public enum Estados
        {
            AGUA = '.',
            BARCO = 'B',
            TOCADO = 'X'
        }

        private Coords coords;
        private Estados estado;

        public Casilla(Coords coords)
        {
            this.coords = coords;
            SetEstado(Estados.AGUA);
        }

        public Casilla(Coords coords, char estado)
        {
            this.coords = coords;
            SetEstado(Estados.AGUA);
        }

        public Coords GetCoords()
        {
            return coords;
        }

        public Estados GetEstado()
        {
            return estado;
        }

        public void SetEstado(Estados estado)
        {
            this.estado = estado;
        }

    }
}