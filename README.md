# Http Request Sporadicator

An HTTP proxy that _sporadicates_ long lasting HTTP requests.
This is useful to prevent blocking of the HTTP client process.

To "sporadicate", means, to split a long-running HTTP request to
several short-lived requests. 

Sporadicator acts as an HTTP proxy, that keeps an open connection to a 
remote HTTP server open, while it interrupts the original connection
from a client, responding immediately with `504 Gateway Timeout`. This 
specific response also includes a unique URL of a REST resource 
that can be used to continue the original request, so that the client
can try to resume the interrupted request.