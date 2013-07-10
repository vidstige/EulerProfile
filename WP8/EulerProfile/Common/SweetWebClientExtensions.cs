using System;
using System.Net;
using System.Threading.Tasks;

namespace EulerProfile.Common
{
    static class SweetWebClientExtensions
    {
        public static Task<string> DownloadString(this WebClient wc, Uri url)
        {
            var tcs = new TaskCompletionSource<string>();            
            wc.DownloadStringCompleted += (s, e) =>
            {
                if (e.Error != null) tcs.TrySetException(e.Error);
                else if (e.Cancelled) tcs.TrySetCanceled();
                else tcs.TrySetResult(e.Result);
            };
            wc.DownloadStringAsync(url);
            return tcs.Task;
        }
    }
}
