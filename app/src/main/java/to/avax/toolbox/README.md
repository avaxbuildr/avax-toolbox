# to.avax.toolbox

AVAX Toolbox root package. All Toolbox functionality is built under this package. 

Note that [AvalancheJ](https://github.com/rektbuildr/avalanchej) packages all live outside `toolbox` but also under `to.avax`  root. 

### Directory Structure

There are 2 main paths inside AVAX Toolbox, which reflect one of its core design principles : to clearly separate networking from offline code.

All code under `airgapped` is guaranteed to run offline

`airgapped/` : Programs meant to be run offline. No network connections allowed.

`networked/` : Programs meant to be run online. Network connections allowed.

*********
*** NOTE: Private keys never leave your computer, under any circumstance, airgapped or not.
*********

`gui/` : Front-end stuff. 


## Additional Documentation

For additional documentation, questions, bug reports and general support visit [Crypto.bi](https://crypto.bi/forum/)
