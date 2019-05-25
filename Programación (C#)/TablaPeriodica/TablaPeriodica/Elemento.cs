using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase Elemento principal, de las que heredarán todos los subtipos
    /// </summary>
    public abstract class Elemento
    {
        private string nombre;
        private string simbolo_quimico;
        private int numero_atomico;
        private DateTime ano_descubrimiento;
        private bool elemento_antiguo;

        public Elemento(string nombre, string simbolo_quimico, int numero_atomico, DateTime ano_descubrimiento, bool elemento_antiguo)
        {
            this.nombre = nombre;
            this.simbolo_quimico = simbolo_quimico;
            this.numero_atomico = numero_atomico;
            this.ano_descubrimiento = ano_descubrimiento;
            this.elemento_antiguo = elemento_antiguo;
        }

        public string GetNombre()
        {
            return nombre;
        }

        public void SetNombre(string nombre)
        {
            this.nombre = nombre;
        }

        public string GetSimboloQuimico()
        {
            return simbolo_quimico;
        }

        public void SetSimboloQuimico(string simbolo_quimico)
        {
            this.simbolo_quimico = simbolo_quimico;
        }

        public int GetNumeroAtomico()
        {
            return numero_atomico;
        }

        public void SetNumeroAtomico(int numero_atomico)
        {
            this.numero_atomico = numero_atomico;
        }
        public DateTime GetAnoDescubrimiento()
        {
            return ano_descubrimiento;
        }

        public int GetAno()
        {
            return ano_descubrimiento.Year;
        }

        public void SetAnoDescubrimiento(DateTime ano_descubrimiento)
        {
            this.ano_descubrimiento = ano_descubrimiento;
        }

        public bool GetElementoAntiguo()
        {
            return elemento_antiguo;
        }

        public void SetElementoAntiguo(bool elemento_antiguo)
        {
            this.elemento_antiguo = elemento_antiguo;
        }

        public  virtual String ToCsv()
        {
            return string.Format("{0};{1};{2};{3};{4}", GetType().ToString().Split('.')[1], GetNombre(), GetSimboloQuimico(), GetNumeroAtomico(), GetElementoAntiguo() ? "A.C." : GetAno().ToString());
        }

    }
}