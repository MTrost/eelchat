(ns com.eelchat.feat.app
  (:require [com.biffweb :as biff :refer [q]]
            [com.eelchat.middleware :as mid]
            [com.eelchat.ui :as ui]
            [xtdb.api :as xt]))

(defn app [{:keys [session biff/db] :as req}]
  (let [{:user/keys [email]} (xt/entity db (:uid session))]
    (ui/page
     {}
     nil
     [:div "Signed in as halleluja " email "."
      (biff/form
       {:action "/auth/signout"
        :class "inline"}
       [:button.text-blue-500.hover:text-blue-800 {:type "submit"}
        "Sign out"])
      "."]
     [:.h-6]
     [:div "Thanks for joining the waitlist. "
      "We'll let you know when eelchat is ready to use."])))

(def features
  {:routes ["/app" {:middleware [mid/wrap-signed-in]}
            ["" {:get app}]]})
