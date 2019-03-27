using System;
using System.Threading;
using System.Net;
using System.IO;

namespace LedManager
{
    internal class Spammer
    {
        //private static readonly HttpClient client = new HttpClient();
        public  static string Url = "https://dweet.io:443/dweet/quietly/for/";
        public static byte Delay = 1;
        public static string Channel = "cesi-27032019";

        internal void start(int v)
        {
            for(byte i=0; i < v; i++)
            {
                Thread.Sleep(Delay);
                MyMessage m = new MyMessage();
                Console.WriteLine(m.GetPayLoad());
                this.Send(m.GetPayLoad());
            }            
        }

        private void Send(string v)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(Url+Channel);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";
            
            using(var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                streamWriter.Write(v);
                streamWriter.Flush();
                streamWriter.Close();
            }

            try
            {
                var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();

                using(var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    var result = streamReader.ReadToEnd();
                }
            }catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}