using System;
using EulerProfile.Model;
using System.Net;
using System.Threading.Tasks;

namespace EulerProfile.Repositories
{
    class ProjectEulerRepository
    {
        public static Task<string> DownloadString(Uri url)
        {
            var tcs = new TaskCompletionSource<string>();
            var wc = new WebClient();
            wc.DownloadStringCompleted += (s, e) =>
            {
                if (e.Error != null) tcs.TrySetException(e.Error);
                else if (e.Cancelled) tcs.TrySetCanceled();
                else tcs.TrySetResult(e.Result);
            };
            wc.DownloadStringAsync(url);
            return tcs.Task;
        }

        public async Task<UserProfile> GetByUsername(string username)
        {
            Uri uri = new Uri("http://projecteuler.net/profile/" + username + ".txt");
            string rawProfile = await DownloadString(uri);
            var parts = rawProfile.Split(',');
            if (parts.Length < 5) throw new ApplicationException(string.Format("User {0} not found", username));
            return new UserProfile(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
    }
}
