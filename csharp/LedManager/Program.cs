using System.Threading;

namespace LedManager
{
    class Program
    {
        static void Main(string[] args)
        {
            new Thread(() =>
            {
                Thread.CurrentThread.IsBackground = false;
                Spammer s = new Spammer();
                s.start(1000000);
            }).Start();
        }
    }
}
