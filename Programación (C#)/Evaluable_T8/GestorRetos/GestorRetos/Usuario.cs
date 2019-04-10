using System;
using System.Collections.Generic;
using System.Linq;

namespace GestorRetos
{
    public abstract class Usuario
    {
        private string nickname;
        private string email;
        private List<Reto> retos_resueltos;

        public Usuario(string nickname, string email)
        {
            this.nickname = nickname;
            this.email = email;
            retos_resueltos = new List<Reto>();
        }

        public string GetNickName()
        {
            return nickname;
        }

        public void SetNickName(string nickname)
        {
            this.nickname = nickname;
        }

        public string GetEmail()
        {
            return email;
        }

        public void SetEmail(string email)
        {
            this.email = email;
        }

        public List<Reto> GetRetos()
        {
            return retos_resueltos;
        }

        public void SetRetoResuelto(Reto reto)
        {
            retos_resueltos.Add(reto);
        }

        public static bool CountByNickName(List<Usuario> usuarios, string nickname)
        {
            bool existe = false;

            if(null != usuarios.FirstOrDefault(x => x.GetNickName().Equals(nickname))){
                existe = true;
            }

            return existe;
        }

        public virtual void Imprimir()
        {
            //
        }

    }
}
