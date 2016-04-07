#!/usr/bin/tcsh

set rootdir=`/bin/dirname $0`

debut:
  nc -l 80  < ${rootdir}/../../../presentation/static/index.html
  goto debut
